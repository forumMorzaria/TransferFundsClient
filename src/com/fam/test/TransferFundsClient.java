package com.fam.test;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import junit.framework.TestCase;

public class TransferFundsClient extends TestCase {
	
	private WebTarget target = null;
		
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:9998/").build();
	}
	
	/**
	 * Method : viewAllAccounts
	 * Testing for : success
	 * Validated by : status code 200
	 */
	@Test
	public void testViewAllAccountsSuccessStatus() {
		Response response = target.path("transferFunds").
	              path("viewAllAccounts").
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 200);
	}
	
	/**
	 * Method : viewAllAccounts
	 * Testing for : failure
	 * Condition : incorrect method name viewAllAccount
	 * Validated by : status code 404
	 */
	@Test
	public void testViewAllAccountsFailure() {
		Response response = target.path("transferFunds").
	              path("viewAllAccount").
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 404);
	}
	
	/**
	 * Method : viewAllAccounts
	 * Testing for : success
	 * Validated by : Text appearing in the response
	 */
	@Test
	public void testViewAllAccountsResponseText() {
		String plainAnswer = 
	            target.path("transferFunds").path("viewAllAccounts").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String partialResponse = "All Account Details:";
		boolean flag = false;
		if(plainAnswer.contains(partialResponse))
			flag=true;
 	    assertTrue(flag);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : success
	 * Validated by : status code 200
	 */
	@Test
	public void testViewAccountSuccess() {
		Response response = target.path("transferFunds").
	              path("viewAccount").
	              queryParam("accountId", 1000001L).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 200);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : success
	 * Validated by : Text appearing in the response
	 */
	@Test
	public void testViewAccountSuccessText() {
		String response = target.path("transferFunds").
	              path("viewAccount").
	              queryParam("accountId", 1000001L).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(String.class);
		boolean flag=false;
		if(response.contains("User Account Details:"))
			flag=true;
	    
	    assertTrue(flag);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : failure
	 * Condition : incorrect method name viewAccountDetails
	 * Validated by : status code 404
	 */
	@Test
	public void testViewAccountFailure() {
		Response response = target.path("transferFunds").
	              path("viewAccountDetails").
	              queryParam("accountId", 1000001L).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 404);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : failure
	 * Condition : incorrect accountId 1L
	 * Validated by : status code 422
	 */
	@Test
	public void testViewAccountFailureInvalidInput1() {
		Response response = target.path("transferFunds").
	              path("viewAccount").
	              queryParam("accountId", 1L).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 422);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : failure
	 * Condition : null accountId
	 * Validated by : status code 422
	 */
	@Test
	public void testViewAccountFailureInvalidInput2() {
		Response response = target.path("transferFunds").
	              path("viewAccount").
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 422);
	}
	
	/**
	 * Method : transfer
	 * Testing for : success
	 * Validated by : status code 200
	 */
	@Test
	public void testTransferSuccess() {
		Response response = target.path("transferFunds").
	              path("transfer").
	              queryParam("amount", 50).
	              queryParam("from", 1000001).
	              queryParam("to",1000002).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 200);
	}
	
	/**
	 * Method : viewAccount
	 * Testing for : success
	 * Validated by : text appearing in the response
	 */
	@Test
	public void testTransferSuccessText() {
		String response = target.path("transferFunds").
	              path("transfer").
	              queryParam("amount", 50).
	              queryParam("from", 1000001).
	              queryParam("to",1000002).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(String.class);
		boolean flag = false;
		if(response.contains("Transferring Amount"))
			flag=true;
	    assertTrue(flag);
	}
	
	/**
	 * Method : transfer
	 * Testing for : failure
	 * Condition : incorrect method name transferring
	 * Validated by : status code 404
	 */
	@Test
	public void testTransferFailure() {
		Response response = target.path("transferFunds").
	              path("transferring").
	              queryParam("amount", 500).
	              queryParam("from", 1000001).
	              queryParam("to",1000002).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 404);
	}

	/**
	 * Method : transfer
	 * Testing for : failure
	 * Condition : incorrect accountId From
	 * Validated by : status code 422
	 */
	@Test
	public void testTransferFailureInvalidInput1() {
		Response response = target.path("transferFunds").
	              path("transfer").
	              queryParam("amount", 500).
	              queryParam("from", 10000011).
	              queryParam("to",1000002).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 422);
	}
	
	/**
	 * Method : transfer
	 * Testing for : failure
	 * Condition : incorrect accountId To
	 * Validated by : status code 422
	 */
	@Test
	public void testTransferFailureInvalidInput2() {
		Response response = target.path("transferFunds").
	              path("transfer").
	              queryParam("amount", 500).
	              queryParam("from", 1000001).
	              queryParam("to",10000021).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 422);
	}
	
	/**
	 * Method : transfer
	 * Testing for : failure
	 * Condition : insufficient funds
	 * Validated by : status code 422
	 */
	@Test
	public void testTransferFailureInvalidInput3() {
		Response response = target.path("transferFunds").
	              path("transfer").
	              queryParam("amount", 5000).
	              queryParam("from", 1000001).
	              queryParam("to",1000002).
	              request().
	              accept(MediaType.TEXT_PLAIN).
	              get(Response.class);
	    int status = response.getStatus();
	    assertEquals(status, 422);
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient();
	    target = client.target(getBaseURI());
	    
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
}
