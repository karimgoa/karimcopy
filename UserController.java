package com.myobjects;
import java.util.*;
import java.util.Locale;
import com.myobjects.*;
import java.io.IOException;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.*; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.RequestContext;


import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.LocaleResolver;


import org.springframework.web.servlet.support.RequestContextUtils;



@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	
	//private MessageSource messages;
	//public void setMessages(MessageSource messages) {
	//	this.messages = messages;
	//	System.out.println("hello " + messages);
	//	}

	
	@RequestMapping(value="/helloWorld")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User u,BindingResult result) throws Exception {
		
		//User u=new User();
		System.out.println(u.toString());
		System.out.println(u.getFirstname());
		System.out.println(u.getLastname());
		System.out.println(u.toString());
		u.setLastname("karim");
		 
		userValidator.validate(u, result);
		System.out.println(result.toString());
	//	String message = messages.getMessage("error.required",	null, Locale.getDefault());
	//	ApplicationContext ctx = new FileSystemXmlApplicationContext(
      //  "springmvc4tutorial-servlet.xml");

//Locale english = Locale.ENGLISH;
//Locale czech = new Locale("cs", "CZ");

//System.out.println(ctx.getMessage("error.required", null, english));
		
		String msg = new RequestContext(request).getMessage("error.required");
		System.out.println(msg);
		//msg = new RequestContext(request).getMessage("error.required",null,"en_IN");
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        LocaleEditor localeEditor = new LocaleEditor();
        if (localeResolver != null) {
            // get current locale
            Locale locale = localeResolver.resolveLocale(request);
            System.out.println(locale.toString());
            localeEditor.setAsText("en_IN");
            localeResolver.setLocale(request, response,(Locale) localeEditor.getValue());

            

        }
	
        msg = new RequestContext(request).getMessage("error.required",request.getLocale().toString());
		System.out.println(msg);
        
		
		
		return new ModelAndView("helloworld.jsp");
	}

	
}
