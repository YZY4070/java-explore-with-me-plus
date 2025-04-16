package ru.practicum.service.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.category.model.Category;
import ru.practicum.service.event.dto.EventState;
import ru.practicum.service.user.model.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "events", schema = "public")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 120)
    String title;

    @Column(nullable = false, length = 2000)
    String annotation;

    @Column(nullable = false, length = 7000)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(name = "event_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    Location location;

    @Column(nullable = false)
    Boolean paid;

    @Column(name = "participant_limit", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    Integer participantLimit;

    @Column(name = "request_moderation", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean requestModeration;

    @Column(name = "confirmed_requests", columnDefinition = "BIGINT DEFAULT 0")
    Long confirmedRequests;

    @Column(name = "created_on", nullable = false)
    LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id", nullable = false)
    User initiator;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    EventState state;

    @Column(name = "published_on")
    LocalDateTime publishedOn;

    @Transient
    Long views;
}
