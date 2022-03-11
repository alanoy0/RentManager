package EPF;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.epf.rentmanager.model.Client;

public class ClientServiceTest {
   @Test
   void isLegal_should_return_true_when_age_is_over_18() {
       // Given
	   LocalDate birth = LocalDate.now();
	   birth.minusYears(20);
       Client legalClient = new Client(20,"John", "Doe", "john.doe@ensta.fr",birth);
        
       // Then
       assertTrue(Clients.isLegal(legalClient));
   }

   @Test
   void isLegal_should_return_false_when_age_is_under_18() {
        // Given   
        Client illegaUser = new Client(20,"John", "Doe", "john.doe@ensta.fr",LocalDate.now());
        
       // Then
       assertFalse(Clients.isLegal(illegaUser));
   }
}
