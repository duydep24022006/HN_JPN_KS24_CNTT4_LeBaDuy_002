package com.example.cntt4_lebaduy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "artifacts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên hiện vật không được để trống")
    @Size(min = 5, max = 150, message = "Tên hiện vật phải từ 5 đến 150 ký tự")
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotBlank(message = "Nguồn gốc không được để trống hoặc chỉ chứa khoảng trắng")
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull(message = "Ngày tiếp nhận không được để trống")
    @PastOrPresent(message = "Ngày tiếp nhận không được là ngày trong tương lai")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Artifact(String name, String origin, LocalDate date) {
        this.name = name;
        this.origin = origin;
        this.date = date;
    }
}
