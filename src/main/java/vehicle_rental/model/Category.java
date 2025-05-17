package vehicle_rental.model;

public class Category extends BaseModel{


    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, User createdUser, User updatedUser) {
        this.name = name;
        this.setCreatedUser(createdUser);
        this.setUpdatedUser(updatedUser);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
