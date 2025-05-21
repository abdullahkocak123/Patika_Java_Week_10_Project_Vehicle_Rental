package vehicle_rental.service;

import vehicle_rental.dao.RentItemDAO;
import vehicle_rental.model.RentItem;

import java.util.List;

public class RentItemsService {

    private final RentItemDAO rentItemDAO;

    public RentItemsService() {
        this.rentItemDAO = new RentItemDAO();
    }

    public void save(List<RentItem> rentItems){

        rentItemDAO.saveAll(rentItems);
        System.out.println("Siparişteki araçlar kaydedildi.");
    }
}
