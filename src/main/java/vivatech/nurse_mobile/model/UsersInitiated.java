package vivatech.nurse_mobile.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mh_users_intiated")
public class UsersInitiated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "mobile_number", nullable = false)
    private int mobileNumber;

    @Column(name = "promo_code_of")
    private Integer promoCodeOf;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}

