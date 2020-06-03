/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.dinardap.interoperadorv2.cliente.servicio;


import ec.gob.dinardap.interoperadorv2.ws.Columna;
import ec.gob.dinardap.interoperadorv2.ws.Consultar;
import ec.gob.dinardap.interoperadorv2.ws.ConsultarFaultException;
import ec.gob.dinardap.interoperadorv2.ws.ConsultarResponse;
import ec.gob.dinardap.interoperadorv2.ws.Entidad;
import ec.gob.dinardap.interoperadorv2.ws.Fila;
import ec.gob.dinardap.interoperadorv2.ws.Interoperador;
import ec.gob.dinardap.interoperadorv2.ws.Parametro;
import ec.gob.dinardap.interoperadorv2.ws.Parametros;
import java.util.List;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;


/**
 *
 * @author jadira.paspuel
 */
public class ServicioDINARDAP {
	
		public void getDatosDINARDAP(String cedula,String paquete) {
        try {

            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(Interoperador.class);
            factory.setAddress("https://interoperabilidad.dinardap.gob.ec/interoperador-v2?wsdl");

            /**
             * ****************
             * cambiar usurio *****************
             */
            factory.setUsername("DtDsEgNomINAL");
            /**
             * ****************
             * cambiar password *******************
             */
            factory.setPassword("F6ve##Bgq2QV");

            Interoperador port = (Interoperador) factory.create();
            Client client = ClientProxy.getClient(port);

            if (client != null) {
                HTTPConduit conduit = (HTTPConduit) client.getConduit();
                HTTPClientPolicy policy = new HTTPClientPolicy();
                //policy.setConnectionTimeout(ParametrosUtil.CONNECTION_TIMEOUT);
                //policy.setReceiveTimeout(ParametrosUtil.RECEIVE_TIMEOUT);
                policy.setAllowChunking(false);
                conduit.setClient(policy);

            }
            Parametro paramCodigoPaquete = new Parametro();
            paramCodigoPaquete.setNombre("codigoPaquete");
            //paramCodigoPaquete.setValor("2640");
            //paramCodigoPaquete.setValor("2634");
            paramCodigoPaquete.setValor(paquete);

            Parametro paramIdent = new Parametro();
            paramIdent.setNombre("identificacion");
            paramIdent.setValor(cedula);

            Parametros parametros = new Parametros();
            parametros.getParametro().add(paramCodigoPaquete);
            parametros.getParametro().add(paramIdent);

            Consultar consultar = new Consultar();
            consultar.setParametros(parametros);
            ConsultarResponse response = port.consultar(consultar);

            //System.out.println(response.getReturn().getCodigoPaquete());
            //System.out.println("MENSAJE PRINCIPAL:"+response.getReturn().getMensaje());
            List<Entidad> entidades = response.getPaquete().getEntidades().getEntidad();

            for (Entidad entidad : entidades) {
//                filas
                System.out.println("Información de:" + entidad.getNombre());
                System.out.println("------------------------------------------------------------");
                for (Fila fila : entidad.getFilas().getFila()) {
//                    columnas
                    for (Columna columna : fila.getColumnas().getColumna()) {
//                        se obtiene los valores de cada columna
                        System.out.println(columna.getCampo() + " = " + columna.getValor());

                    }
                    System.out.println("------------------------------------------------------------");
                }

            }

        } catch (ConsultarFaultException ex) {
            ex.printStackTrace(System.out);
        }
    }
			
		public ConsultarResponse obtenerDatosInteroperabilidad(String cedula,String paquete) {
	        try {

	            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	            factory.setServiceClass(Interoperador.class);
	            factory.setAddress("https://interoperabilidad.dinardap.gob.ec/interoperador-v2?wsdl");

	            /**
	             * ****************
	             * cambiar usurio *****************
	             */
	            factory.setUsername("DtDsEgNomINAL");
	            /**
	             * ****************
	             * cambiar password *******************
	             */
	            factory.setPassword("F6ve##Bgq2QV");

	            Interoperador port = (Interoperador) factory.create();
	            Client client = ClientProxy.getClient(port);

	            if (client != null) {
	                HTTPConduit conduit = (HTTPConduit) client.getConduit();
	                HTTPClientPolicy policy = new HTTPClientPolicy();
	                //policy.setConnectionTimeout(ParametrosUtil.CONNECTION_TIMEOUT);
	                //policy.setReceiveTimeout(ParametrosUtil.RECEIVE_TIMEOUT);
	                policy.setAllowChunking(false);
	                conduit.setClient(policy);

	            }
	            Parametro paramCodigoPaquete = new Parametro();
	            paramCodigoPaquete.setNombre("codigoPaquete");
	            //paramCodigoPaquete.setValor("2640");
	            //paramCodigoPaquete.setValor("2634");
	            paramCodigoPaquete.setValor(paquete);

	            Parametro paramIdent = new Parametro();
	            paramIdent.setNombre("identificacion");
	            paramIdent.setValor(cedula);

	            Parametros parametros = new Parametros();
	            parametros.getParametro().add(paramCodigoPaquete);
	            parametros.getParametro().add(paramIdent);

	            Consultar consultar = new Consultar();
	            consultar.setParametros(parametros);
	            ConsultarResponse response = port.consultar(consultar);

	           return response;

	        } catch (ConsultarFaultException ex) {
	        	
	        	ex.printStackTrace(System.out);
	        	return null;
	            
	        }
	    }
		public ConsultarResponse obtenerDatosPorCedula(String cedula,String paquete) {
	        try {

	            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	            factory.setServiceClass(Interoperador.class);
	            factory.setAddress("https://interoperabilidad.dinardap.gob.ec/interoperador-v2?wsdl");

	            /**
	             * ****************
	             * cambiar usurio *****************
	             */
	            factory.setUsername("DtDsEgNomINAL");
	            /**
	             * ****************
	             * cambiar password *******************
	             */
	            factory.setPassword("F6ve##Bgq2QV");

	            Interoperador port = (Interoperador) factory.create();
	            Client client = ClientProxy.getClient(port);

	            if (client != null) {
	                HTTPConduit conduit = (HTTPConduit) client.getConduit();
	                HTTPClientPolicy policy = new HTTPClientPolicy();
	                //policy.setConnectionTimeout(ParametrosUtil.CONNECTION_TIMEOUT);
	                //policy.setReceiveTimeout(ParametrosUtil.RECEIVE_TIMEOUT);
	                policy.setAllowChunking(false);
	                conduit.setClient(policy);

	            }
	            Parametro paramCodigoPaquete = new Parametro();
	            paramCodigoPaquete.setNombre("codigoPaquete");
	            //paramCodigoPaquete.setValor("2640");
	            //paramCodigoPaquete.setValor("2634");
	            paramCodigoPaquete.setValor(paquete);

	            Parametro paramIdent = new Parametro();
	            paramIdent.setNombre("cedula");
	            paramIdent.setValor(cedula);

	            Parametros parametros = new Parametros();
	            parametros.getParametro().add(paramCodigoPaquete);
	            parametros.getParametro().add(paramIdent);

	            Consultar consultar = new Consultar();
	            consultar.setParametros(parametros);
	            ConsultarResponse response = port.consultar(consultar);

	           return response;

	        } catch (ConsultarFaultException ex) {
	        	
	        	ex.printStackTrace(System.out);
	        	return null;
	            
	        }
	    }	
		public ConsultarResponse obtenerDatosFuente(String parametro, String documento,String paquete,String usuario,String clave) {
	        try {

	            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	            factory.setServiceClass(Interoperador.class);
	            factory.setAddress("https://interoperabilidad.dinardap.gob.ec/interoperador-v2?wsdl");

	            /**
	             * ****************
	             * cambiar usurio *****************
	             */
	            factory.setUsername(usuario);
	            /**
	             * ****************
	             * cambiar password *******************
	             */
	            factory.setPassword(clave);

	            Interoperador port = (Interoperador) factory.create();
	            Client client = ClientProxy.getClient(port);

	            if (client != null) {
	                HTTPConduit conduit = (HTTPConduit) client.getConduit();
	                HTTPClientPolicy policy = new HTTPClientPolicy();
	                //policy.setConnectionTimeout(ParametrosUtil.CONNECTION_TIMEOUT);
	                //policy.setReceiveTimeout(ParametrosUtil.RECEIVE_TIMEOUT);
	                policy.setAllowChunking(false);
	                conduit.setClient(policy);

	            }
	            Parametro paramCodigoPaquete = new Parametro();
	            paramCodigoPaquete.setNombre("codigoPaquete");
	            paramCodigoPaquete.setValor(paquete);

	            Parametro paramIdent = new Parametro();
	            paramIdent.setNombre(parametro);
	            paramIdent.setValor(documento);

	            Parametros parametros = new Parametros();
	            parametros.getParametro().add(paramCodigoPaquete);
	            parametros.getParametro().add(paramIdent);

	            Consultar consultar = new Consultar();
	            consultar.setParametros(parametros);
	            ConsultarResponse response = port.consultar(consultar);

	           return response;

	        } catch (ConsultarFaultException ex) {
	        	
	        	ex.printStackTrace(System.out);
	        	return null;
	            
	        }
	    }
}
