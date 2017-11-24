package praveenkumar.push.notification.PushNotification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created Praveenkumar on 24/11/2017
 */
@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "notification")
    private String notification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
