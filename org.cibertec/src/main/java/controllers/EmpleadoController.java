package controllers;

import java.math.BigDecimal;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import models.Empleado;

public class EmpleadoController {
	
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//Registrar
		public String createEmpleado(String Apellidos, String Nombres, int Edad, String Sexo, double Salario) {
			try {
				Empleado empleado = new Empleado(Apellidos, Nombres, Edad, Sexo, Salario);
				
				session.beginTransaction();
				session.save(empleado);
				session.getTransaction().commit();
				sessionFactory.close();
				
				return "Empleado creado";
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return "Error al registrar empleado";
		}
		
		//Eliminar
		public String deleteEmpleado(int idEmpleado) {
			try {
				
				session.beginTransaction();
				Empleado empleado = session.get(Empleado.class,idEmpleado);
				session.delete(empleado);
				session.getTransaction().commit();
				sessionFactory.close();
				
				return "Empleado eliminado correctamente";
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return "Error al eliminar empleado";
		}
		
		//Actualizar
		public String updateEmpleado(int idEmpleado,String Nombres) {
			try {
				session.beginTransaction();
				Empleado empleado = session.get(Empleado.class,idEmpleado);
				empleado.setNombres(Nombres);
				session.update(empleado);
				session.getTransaction().commit();
				sessionFactory.close();
				
				return "Empleado actualizado correctamente";
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return "Error al actualizar empleado";
		}
		
		//Buscar
		public String getEmpleado(int idEmpleado) {
			try {
				session.beginTransaction();
				Empleado empleado = session.get(Empleado.class,idEmpleado);
				session.getTransaction().commit();
				sessionFactory.close();
		
		            return "Empleado encontrado";

			}
			catch(Exception e) {
				e.printStackTrace();
			}			
			return "Empleado no encontrado";
		}
		
	}