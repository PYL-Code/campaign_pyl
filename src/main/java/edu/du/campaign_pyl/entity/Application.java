package edu.du.campaign_pyl.entity;

import edu.du.campaign_pyl.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    @ManyToOne(fetch = FetchType.EAGER) //TODO: 서비스할 경우 lazy로 변경하는 게 더 안정적
    @JoinColumn(name = "campaign_no", nullable = false)
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.EAGER) //TODO: 서비스할 경우 lazy로 변경하는 게 더 안정적
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
