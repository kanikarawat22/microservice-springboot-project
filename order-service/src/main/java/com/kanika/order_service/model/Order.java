    package com.kanika.order_service.model;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import jakarta.persistence.*;
    import java.util.List;

    @Entity                                        // Marks this class as a JPA entity (maps to a DB table)
    @Table(name = "t_orders")                     // Specifies the table name in the database
    @Getter                                        // Lombok: Generates getter methods
    @Setter                                        // Lombok: Generates setter methods
    @NoArgsConstructor                             // Lombok: Generates a no-arg constructor
    @AllArgsConstructor                            // Lombok: Generates a constructor with all fields
    public class Order {

        @Id                                        // Marks 'id' as the primary key
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;                            // Auto-generated primary key

        private String orderNumber;                // Unique order number (UUID from service layer)

        @OneToMany(cascade = CascadeType.ALL)      // One Order → Many OrderLineItems  Cascade.ALL = save/update/delete child items automatically
        private List<OrderLineItems> orderLineItemsList;  // List of all items inside this order
    }
