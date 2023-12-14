package com.kyungil.webcar.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kyungil.webcar.product.dao.CarDAO;
import com.kyungil.webcar.product.domain.Car;

@Service
public class CarService {
	@Autowired
	private CarDAO carDAO;

	public void addCar(Car car) {
		carDAO.addCar(car);
	}

    public List<Car> getCarList(int brandId) {
        return carDAO.getCarList(brandId);
    }
    public List<Car> getCarTypeList(int carTypeId) {
        return carDAO.getCarTypeList(carTypeId);
    }
	public int getPageCount(int brandId) {
		return carDAO.getCount(brandId);
	}
	public int getTypeCount(int carTyepId) {
		return carDAO.getTypeCount(carTyepId);
	}
    public List<Car> getCarListByPage(int brandId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return carDAO.getCarListByPage(brandId,offset, pageSize);
    }
    public List<Car> getCarTypeListByPage(int carTypeId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return carDAO.getCarTypeListByPage(carTypeId,offset, pageSize);
    }
    public Car get(int id) {
    	Car car = carDAO.get(id);
		return car;
    }
    public void updateLikesCount(int carId, int likesCount) {
        carDAO.updateLikesCount(carId, likesCount);
    }
    public List<Car> getHotList() {
        return carDAO.getHotList();
    }
	public List<Car> getBestList(int carTyepId) {
		return carDAO.getBestList(carTyepId);
	}
}
