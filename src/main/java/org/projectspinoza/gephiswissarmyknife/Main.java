package org.projectspinoza.gephiswissarmyknife;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.gephiswissarmyknife.Server.GsakServer;


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
	
    public static void main( String[] args ) {
    	GsakServer server = new GsakServer();
    	server.deployServer();
    	server.deployRoutes();
    }
}
