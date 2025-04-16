package ru.practicum.service.event.dto;

public enum EventStateAction {
    // Admin actions
    PUBLISH_EVENT,
    REJECT_EVENT,

    // User actions
    SEND_TO_REVIEW,
    CANCEL_REVIEW;

    public static EventState getByAction(final EventStateAction action) {
        return switch (action) {
            case PUBLISH_EVENT -> EventState.PUBLISHED;
            case REJECT_EVENT, CANCEL_REVIEW -> EventState.CANCELED;
            case SEND_TO_REVIEW -> EventState.PENDING;
        };
    }
}
