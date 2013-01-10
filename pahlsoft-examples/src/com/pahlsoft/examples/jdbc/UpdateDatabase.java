package com.pahlsoft.examples.jdbc;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pahlsoft.examples.common.Global;

public class UpdateDatabase {

	public static String WRITE_OBJECT_SQL = "insert into  PERF.PERF_RT_ACCESS_URLS(URL_GRP_NAME,URL_STR) VALUES (?,?)";
	public static String CHECK_OBJECT_SQL = "select URL_STR from PERF.PERF_RT_ACCESS_URLS WHERE URL_STR = ?";

	//Insert into PERF.PERF_RT_ACCESS_URLS (URL_GRP_NAME,URL_STR) values ('ADF_FILE', ' file:/C:/Ayan/ICEDashboard/GBDS/ADFData/0414/activeOH0414_1.csv');
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		// Define database driver
		String driverName = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		// Set Database connection USER and Password
		String username = "perf";
		String password = "welcome1";
		try {
			if (Global.PROD) {
				conn = DriverManager.getConnection(Global.ProdDBServer, username, password);
			} else if (Global.UAT) {
				conn = DriverManager.getConnection(Global.UatDBServer, username, password);
			} else {
				conn = DriverManager.getConnection(Global.DevDBServer, username, password);
			}
				

		} catch ( SQLException sqe ) {
			System.out.println("Error: getConnection: Unable establish connection with DB ");
			if (Global.DebugEnabled) {
				sqe.printStackTrace();
			}
		}

		return conn;
	}

	public boolean writeDBURI( String foundFile ) throws Exception {

		Connection conn = getConnection();
		Boolean ret = Boolean.FALSE;
		int chkResults;
		String fileType = "";
		CallableStatement cstmt = conn.prepareCall(WRITE_OBJECT_SQL);
		
		try {
			//System.out.println("UpdateDatabase: Inserting file:/" + foundFile);
            if (foundFile.contains("viewable")) {
            	fileType = "ADF_FILE";
            } else {
            	fileType = "ADF_ACTIVE_FILE";
            }
            	
			// set DB input parameters for the insertion
			cstmt.setString(1, fileType);
			cstmt.setString(2, "http://mrdevjw6.svr.us.jpmchase.net:50075/streamFile?filename=" + foundFile);
			
			// Execute the SQL Code
			chkResults = cstmt.executeUpdate();
			conn.commit();
			
			// Gather boolean return code for insertion/update

			if (chkResults > 0) {
				System.out.println("FileWatcher: Database Insert - SUCCESS");
				ret = Boolean.TRUE;

			} else {
				System.out.println("FileWatcher: Database Insert - FAILURE");
				ret = Boolean.FALSE;
			}

		} catch ( SQLException sqe ) {
			//System.out.println("Error: writeTestCode: Unable update database. ");
			//if (Global.DebugEnabled) {
				sqe.printStackTrace();
			//}
		} finally {
			//Close SQL Connection
			cstmt.close();  	
		}
		return ret;
	}
	public boolean checkDBURI( String foundFile ) throws Exception {

		Connection conn = getConnection();
		Boolean ret = Boolean.FALSE;
		
		CallableStatement cstmt = conn.prepareCall(CHECK_OBJECT_SQL);
		try {

			//System.out.println("UpdateDatabase: checkDBURI: Checking for file:/" + Global.FileWatchLocation +"/" + foundFile);

			// set DB input parameters for the insertion
			cstmt.setString(1, "http://mrdevjw6.svr.us.jpmchase.net/streamFile?filename=" + foundFile);

			ResultSet rset = cstmt.executeQuery();
	    while (rset.next()) {
	      //System.out.println("DEBUG: checkDBURI: Already Exists: " + rset.getString(1));
	      ret = Boolean.TRUE;
	    }
	    rset.close();

		} catch ( SQLException sqe ) {
			//System.out.println("Error: writeTestCode: Unable update database. ");
			//if (Global.DebugEnabled) {
				sqe.printStackTrace();
			//}
		} finally {
			//Close SQL Connection
			cstmt.close();  	
		}
		return ret;
	}
	

}
