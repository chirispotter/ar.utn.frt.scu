/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package finger;

import com.griaule.grFinger.Context;
import com.griaule.grFinger.FingerCallBack;
import com.griaule.grFinger.FingerprintImage;
import com.griaule.grFinger.FingerprintTemplate;
import com.griaule.grFinger.GrErrorException;
import com.griaule.grFinger.GrFinger;
import com.griaule.grFinger.ImageCallBack;
import com.griaule.grFinger.MatchingResult;
import com.griaule.grFinger.StatusCallBack;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author JP
 */
public class clsFinger implements StatusCallBack,ImageCallBack,FingerCallBack{

    private GrFinger grFinger;
  	private FingerprintImage fingerprint; // Ultima Imagen Adquirida por el SENSOR
	private FingerprintTemplate template; // Ultimo Template sacado de la Ultima imagen Adquirida por el SENSOR
    private formularios.frmPrincipal formPrincipal;

    public clsFinger(){

        try {
             this.formPrincipal.escribirLog("##### Inicializando CLASE Finger Print #####");
          	grFinger = new GrFinger();
 			grFinger.initializeCapture(this);

 		} catch (GrErrorException e) {
             this.formPrincipal.escribirLog("Error al levantar la clase GrFinger o al Inicializar la Captura");
        }


    }
    
    public clsFinger(formularios.frmPrincipal form){

        try {
            
          	grFinger = new GrFinger();
 			grFinger.initializeCapture(this);
            this.formPrincipal=form;
            this.formPrincipal.escribirLog("##### Inicializando CLASE Finger Print #####");

 		} catch (GrErrorException e) {
             this.formPrincipal.escribirLog("Error al levantar la clase GrFinger o al Inicializar la Captura");
        }


    }

    public void onPlug(String idSensor) {
         System.out.println("#### Se ejecuto el metodo onPlug #### [ ID del Sensor: "+idSensor+" ]");

  		try {
         System.out.println("#### Leyendo Huella... #### [ ID del Sensor: "+idSensor+" ]");
			grFinger.startCapture(idSensor,this,this);
		} catch (GrErrorException e) {
			 System.out.println("#### ERROR al tratar de capturar la Huella #### [ ID del Sensor: "+idSensor+" ]");
		}


    }

    public void onUnplug(String idSensor) {
         this.formPrincipal.escribirLog("#### Se ejecuto el metodo onUnplug #### [ ID del Sensor: "+idSensor+" ]");
         

        try {
        	grFinger.stopCapture(idSensor);
             this.formPrincipal.escribirLog("#### Huella Leida Correctamente #### [ ID del Sensor: "+idSensor+" ]");
		} catch (GrErrorException e) {
             this.formPrincipal.escribirLog("#### ERROR al tratar de dejar de leer la HUELLA #### [ ID del Sensor: "+idSensor+" ]");
        }
    }

    public void onImage(String idSensor, FingerprintImage fingerprint) {
        try {
            this.formPrincipal.escribirLog("#### Se ejecuto el metodo onImage #### [ ID del Sensor: " + idSensor + " ] ");
            this.fingerprint = fingerprint;
            this.formPrincipal.mostrarImagenHuella(fingerprint.newImageProducer());
        } catch (GrErrorException ex) {
            this.formPrincipal.escribirLog("#### ERROR en el metodo OnImage #### [ ID del Sensor: " + idSensor + " ] ");
        }
    }

    public void onFingerDown(String idSensor) {
         this.formPrincipal.escribirLog("#### Se ejecuto el metodo onFingerDown #### [ ID del Sensor: "+idSensor+" ]");
    }

    public void onFingerUp(String idSensor) {
        this.formPrincipal.escribirLog("#### Se ejecuto el metodo onFingerUp #### [ ID del Sensor: "+idSensor+" ]");
        this.formPrincipal.setSensorID(idSensor);
        this.extraerTemplateHuella();
        this.validarHuella();
    }

    public void extraerTemplateHuella(){
         this.formPrincipal.escribirLog("#### Extrayendo TEMPLATE de Huella #### ");
		try {
			template = grFinger.extract(fingerprint,Context.DEFAULT_CONTEXT);
			switch (template.getImageQuality()){
				case FingerprintTemplate.GR_HIGH_QUALITY:
					 this.formPrincipal.escribirLog("-- Calidad del la Huella:  Excelente -- ");
					break;
				case FingerprintTemplate.GR_MEDIUM_QUALITY:
					 this.formPrincipal.escribirLog("-- Calidad del la Huella:  Normal -- ");
				    break;
				case FingerprintTemplate.GR_BAD_QUALITY:
					 this.formPrincipal.escribirLog("-- Calidad del la Huella:  Baja -- ");
			    	break;
			}
			 
		} catch (GrErrorException e) {
                    this.formPrincipal.escribirLog("#### ERROR en la Extraccion del TEMPLATE de la Huella #### ");
		}

    }

    public void registrarHuella(crud.Ciudadanos ciudadano){
        ciudadano.setHuella(this.template.getData());
        this.persist(ciudadano);
   }

    public void validarHuella(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("scuPU");
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createNamedQuery("Ciudadanos.findAll");
        java.util.List<crud.Ciudadanos> ciudadanos = consulta.getResultList();


        try {

        grFinger.identifyPrepare(template, Context.DEFAULT_CONTEXT);
        byte buffer[] = new byte[GrFinger.GR_MAX_SIZE_TEMPLATE];



        for(crud.Ciudadanos alumno : ciudadanos){

            buffer = alumno.getHuella();

            if(buffer!=null){
                FingerprintTemplate referenceTamplate = new FingerprintTemplate(buffer,alumno.getHuella().length);
                MatchingResult result = grFinger.identify(referenceTamplate,Context.DEFAULT_CONTEXT);

                if (result.doesMatched()){
                    this.formPrincipal.actualizarInfoAlumnoDetectado(alumno);
                     this.formPrincipal.escribirLog("#### HUELLA ENCONTRADA EN LA BASE DE DATOS. Alumno: "+alumno.toString());
                    return;
                }
            }
        }

        //si no encuentra el alumno no le ponemos nada a la interfaz
        crud.Ciudadanos alumnoVacio = new crud.Ciudadanos();
        alumnoVacio.setNombre(" NO REGISTRADO");
        alumnoVacio.setEmail(" NO REGISTRADO");
        alumnoVacio.setBarrio(" NO REGISTRADO");
        this.formPrincipal.actualizarInfoAlumnoDetectado(alumnoVacio);

        } catch (GrErrorException ex) {
            this.formPrincipal.escribirLog(" ### ERROR: Al tratar de validar la Huella digital. ###");
        }
        
    }

    public void cerrar(){
         this.formPrincipal.escribirLog("### CERRANDO la CLASE clsFinger ### ");
        try {
            grFinger.finalizeCapture();
        } catch (GrErrorException ex) {
             this.formPrincipal.escribirLog("### ERROR al intentar cerrar la CLASE clsFinger ###");
        }



    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cursocontrolPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
