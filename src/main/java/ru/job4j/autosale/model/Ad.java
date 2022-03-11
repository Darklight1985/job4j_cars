package ru.job4j.autosale.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    private String photo;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

    private boolean done;

    public static Ad of(String description, Car car, User user, boolean done) {
        Ad ad = new Ad();
        ad.description = description;
        ad.car = car;
        ad.user = user;
        ad.created = new Date(System.currentTimeMillis());
        ad.done = done;
        return ad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreated() {
        return created.toString();
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id && done == ad.done && Objects.equals(description, ad.description)
                && Objects.equals(created, ad.created) && Objects.equals(car, ad.car)
                && Objects.equals(photo, ad.photo) && Objects.equals(user, ad.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, car, photo, user, done);
    }

    @Override
    public String toString() {
        return "Ad{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", car=" + car
                + ", photo='" + photo + '\''
                + ", user=" + user
                + ", done=" + done
                + '}';
    }
}
