package com.hutc.test;
import java.sql.Connection;

import com.hutc.util.*;
//import com.hutc.util.ConnectionFactory;

public class test {

	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection(); 	
		System.out.println(connection);
	}

}
