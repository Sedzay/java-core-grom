package lesson30.hw;

public class DepartmentDAO extends AbstractDAO {
    @Override
    public IdEntity add(IdEntity idEntity) {
        return super.add(idEntity);
    }

    @Override
    public void remove(long id) {
        super.remove(id);
    }

    @Override
    public IdEntity update(IdEntity idEntity) {
        return super.update(idEntity);
    }

    @Override
    public String toString() {
        return "DepartmentDAO{" +
                "departments=" + super.getCollection() +
                '}';
    }
}
