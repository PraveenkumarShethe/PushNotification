package praveenkumar.push.notification.PushNotification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import praveenkumar.push.notification.PushNotification.model.Notification;

/**
 * Created Praveenkumar on 24/11/2017
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long>{
}
