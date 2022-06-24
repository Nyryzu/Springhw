package com.example.springhw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @MappedSuperclass
    @NoArgsConstructor
    @SuperBuilder

    public abstract class AbstractEntity<ID extends Serializable> implements Serializable  {

        public static final String FLD_ID="id";
        public static final String FLD_VERSION = "version";

        @Id
        @Column(name = FLD_ID, unique = true, updatable = false, nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private ID id;

        @JsonIgnore
        @Version
        private Integer version;

        @JsonIgnore
        @CreationTimestamp
        private LocalDateTime created;

        @JsonIgnore
        @UpdateTimestamp
        private LocalDateTime updated;
    }


