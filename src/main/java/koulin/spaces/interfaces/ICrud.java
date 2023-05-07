package koulin.spaces.interfaces;

public interface ICrud<T> {
    public abstract T create(T t);
    public abstract T update(T t, Long id);
    public abstract void delete(Long id);

    public abstract T getById(Long id);
}
