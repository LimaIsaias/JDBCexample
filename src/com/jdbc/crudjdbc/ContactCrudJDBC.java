package com.jdbc.crudjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.connection.ConnectionPostgres;

public class ContactCrudJDBC {

	public void save(Contact contact) {
		Connection connection = this.connection();
		PreparedStatement psql = null;

		String sql = "insert into contact(name, phone, email, dt_cad, obs) values (?,?,?,?,?)";
		try {
			psql = connection.prepareStatement(sql);
			psql.setString(1, contact.getName());
			psql.setString(2, contact.getPhone());
			psql.setString(3, contact.getEmail());
			psql.setDate(4, contact.getDateRegister());
			psql.setString(5, contact.getObservacao());

			psql.executeUpdate();

		} catch (SQLException e) {

			System.out.println("An error occurred during registration : " + e.getMessage());
		} finally {
			try {
				psql.close();
			} catch (SQLException e) {
				System.out.println("An error occurred during finally block : " + e.getMessage());
			}

		}
	}

	public void update(Contact contact) {
		Connection connection = this.connection();
		PreparedStatement psql = null;

		String sql = "UPDATE public.contact SET name=?, phone=?, email=?, dt_cad=?, obs=?  where id=?";
		try {
			psql = connection.prepareStatement(sql);
			psql.setString(1, contact.getName());
			psql.setString(2, contact.getPhone());
			psql.setString(3, contact.getEmail());
			psql.setDate(4, contact.getDateRegister());
			psql.setString(5, contact.getObservacao());
			psql.setInt(6, contact.getId());

			psql.executeUpdate();

		} catch (SQLException e) {

			System.out.println("An error occurred during update : " + e.getMessage());
		} finally {
			try {
				psql.close();
			} catch (SQLException e) {
				System.out.println("An error occurred during finally block : " + e.getMessage());
			}

		}

	}

	public void delete(Contact contact) {

		Connection connection = this.connection();
		PreparedStatement psql = null;

		String sql = "	DELETE FROM public.contact WHERE name = ? , phone = ?, email = ? ";
		try {
			psql = connection.prepareStatement(sql);
			psql.setString(1, contact.getName());
			psql.setString(2, contact.getPhone());
			psql.setString(3, contact.getEmail());

			psql.executeUpdate();

		} catch (SQLException e) {

			System.out.println("An error occurred during delete : " + e.getMessage());
		} finally {
			try {
				psql.close();
			} catch (SQLException e) {
				System.out.println("An error occurred during finally block : " + e.getMessage());
			}

		}

	}

	public List<Contact> getContacts() {
		Connection connection = this.connection();
		List<Contact> contacts = new ArrayList<Contact>();

		Statement consult = null;
		ResultSet result = null;
		Contact contact = null;

		String sql = "select * from contact";

		try {
			consult = connection.createStatement();
			result = consult.executeQuery(sql);

			while (result.next()) {
				contact = new Contact();
				contact.setName(result.getString("name"));

				contact.setEmail(result.getString("email"));

				contact.setPhone(result.getString("phone"));

				contact.setDateRegister(result.getDate("dt_cad"));

				contact.setObservacao(result.getString("obs"));

				contact.setId(result.getInt("id"));

				contacts.add(contact);
			}
		} catch (Exception e) {

			System.out.println("An error occurred during consult : " + e.getMessage());

		} finally {
			try {
				consult.close();
				result.close();
				connection.close();

			} catch (Exception e) {
				System.out.println("An error occurred during finally block : " + e.getMessage());
			}
		}
		return contacts;
	}

	public void deleteById(int id) {

		Connection connection = this.connection();
		PreparedStatement psql = null;

		String sql = "	DELETE FROM public.contact WHERE id = ?";
		try {
			psql = connection.prepareStatement(sql);
			psql.setInt(1, id);

			psql.executeUpdate();

		} catch (SQLException e) {

			System.out.println("An error occurred during delete : " + e.getMessage());
		} finally {
			try {
				psql.close();
			} catch (SQLException e) {
				System.out.println("An error occurred during finally block : " + e.getMessage());
			}

		}

	}

	public Connection connection() {
		try {
			return ConnectionPostgres.createConnectionToPostgres();
		} catch (Exception e) {
			System.out.println("An error occurred during finally block : " + e.getMessage());
		}
		return null;

	}

	public static void main(String[] args) {
		ContactCrudJDBC contactCrudJDBC = new ContactCrudJDBC();

		// fist contact
		Contact contact1 = new Contact();
		contact1.setName("Paula Ellen");
		contact1.setEmail("Ellen_gatinha_msn@hotmail.com");
		contact1.setPhone("97643128");
		contact1.setObservacao("Minha Princesa!");
		contact1.setDateRegister(new Date(System.currentTimeMillis()));
		contactCrudJDBC.save(contact1);

		// second contact
		Contact contact2 = new Contact();
		contact2.setName("Ana Isis");
		contact2.setEmail("ana_isis@hotmail.com");
		contact2.setPhone("9781524");
		contact2.setObservacao("Minha Princesinha!");
		contact2.setDateRegister(new Date(System.currentTimeMillis()));
		contactCrudJDBC.save(contact2);

		System.out.println("Contatos Salvos " + contactCrudJDBC.getContacts().size());

	}

}
