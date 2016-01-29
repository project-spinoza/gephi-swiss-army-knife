package org.projectspinoza.gephiswissarmyknife;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.gephiswissarmyknife.Server.GsakServer;

import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Main
 * 
 * @author org.projectspinoza
 * @version 1.0.0
 *
 */
public class Main 
{
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger(Main.class);
	private static Injector injector = Guice.createInjector();
	
	//temp
	public static String graphfile;
	
    public static void main( String[] args ) {
    	graphfile = args[0];
    	GsakServer server = injector.getInstance(GsakServer.class);
    	server.deployServer();
    	server.deployGsakRoutes();
    }
}
