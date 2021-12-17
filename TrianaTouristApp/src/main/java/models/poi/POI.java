package models.poi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.category.Category;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class POI implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @NotEmpty
    private String location;

    private String description;

    @Past
    private LocalDateTime date;

    @ManyToOne
    private Category category;

    @URL
    @NotNull
    private String coverPhoto;

    @URL
    private List<String> photos;
}
