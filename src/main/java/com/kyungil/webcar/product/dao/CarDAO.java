package com.kyungil.webcar.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.product.domain.Car;

@Repository
public class CarDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Car> mapper = new RowMapper<Car>() {
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Car(rs.getInt("id"), rs.getString("name"), rs.getString("content"), rs.getInt("price"),
					rs.getInt("likes_count"), rs.getInt("img_id"), rs.getInt("brand_id"), rs.getInt("cartype_id"),
					rs.getString("url"));
		}
	};

	public void addCar(Car car) {
		jdbcTemplate.update(
				"INSERT INTO car (name, content,price, likes_count,img_id,brand_id,cartype_id) VALUES (?,?,?,?,?,?,?)",
				car.getName(), car.getContent(), car.getPrice(), 0, car.getImgId(), car.getBrandId(),
				car.getCarTypeId());
	}

	public List<Car> getCarList(int brandId) {
		return jdbcTemplate.query(
				"select car.*, img.url from car join img on car.img_id = img.id where car.brand_id = ? order by car.id",
				mapper, brandId);
	}

	public List<Car> getCarTypeList(int carTyeId) {
		return jdbcTemplate.query(
				"select car.*, img.url from car join img on car.img_id = img.id where car.cartype_id = ? order by car.id",
				mapper, carTyeId);
	}

	public List<Car> getCarListByPage(int brandId, int offset, int pageSize) {
		String sql = "SELECT car.*, img.url FROM car JOIN img ON car.img_id = img.id WHERE car.brand_id = ? ORDER BY car.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, mapper, brandId, offset, pageSize);
	}

	public List<Car> getCarTypeListByPage(int carTypeId, int offset, int pageSize) {
		String sql = "SELECT car.*, img.url FROM car JOIN img ON car.img_id = img.id WHERE car.cartype_id = ? ORDER BY car.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, mapper, carTypeId, offset, pageSize);
	}

	public int getCount(int brandId) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM car where brand_id = ?", Integer.class, brandId);
	}

	public int getTypeCount(int carTyepId) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM car where cartype_id = ?", Integer.class, carTyepId);
	}

	public Car get(int id) {
		return jdbcTemplate.queryForObject("select a.*, b.url from car a join img b on a.img_id = b.id where a.id = ?",
				mapper, id);
	}

	public void updateLikesCount(int carId, int likesCount) {
		String query = "UPDATE car SET likes_count = ? WHERE id = ?";
		jdbcTemplate.update(query, likesCount, carId);
	}

	public List<Car> getBestList(int cartypeId) {
		String sql = "SELECT car.*, img.url FROM car JOIN img ON car.img_id = img.id WHERE car.cartype_id = ? ORDER BY car.likes_count DESC LIMIT 8";

		return jdbcTemplate.query(sql, mapper, cartypeId);
	}
	public List<Car> getHotList() {
		String sql = "SELECT car.*, img.url FROM car JOIN img ON car.img_id = img.id ORDER BY car.likes_count DESC LIMIT 4";

		return jdbcTemplate.query(sql, mapper);
	}
}
