package com.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class ADTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "ldap://mel-dc2:389"; /*test*/
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "Praneeth.Desur@revolutionit.com.au");
		env.put(Context.SECURITY_CREDENTIALS, "@2017");
		 
		try {
			LdapContext ctx = new InitialLdapContext(env, null);
			ctx.setRequestControls(null);
		    System.out.println("connected");
		    System.out.println(ctx.getEnvironment());
		    NamingEnumeration<?> namingEnum = ctx.search("OU=SBSUsers,OU=Users,OU=MyBusiness,DC=revit,DC=local", "(objectclass=user)", getSimpleSearchControls());
	        while (namingEnum.hasMore ()) {		     
	        		// do something useful with the context...
	        	 SearchResult result = (SearchResult) namingEnum.next ();    
	             Attributes attrs = result.getAttributes ();
	             System.out.println(attrs.get("cn"));
	        }
	        namingEnum.close();
		    ctx.close();                  
		 
		} catch (AuthenticationNotSupportedException ex) {
		    System.out.println("The authentication is not supported by the server");
		} catch (AuthenticationException ex) {
		    System.out.println("incorrect password or username");
		    ex.printStackTrace();
		} catch (NamingException ex) {
		    System.out.println("error when trying to create the context");
		}

	}
	
	private static SearchControls getSimpleSearchControls() {
	    SearchControls searchControls = new SearchControls();
	    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    searchControls.setTimeLimit(30000);
	  /*  String[] attrIDs = {"objectGUID"};
	    searchControls.setReturningAttributes(attrIDs); */
	    return searchControls;
	}
	
	

}
