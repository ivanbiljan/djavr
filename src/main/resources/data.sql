insert into student (first_name, last_name, uid, ects_points, date_of_birth, address_line_1, city, country, zip_code) values
                    ('Ivo', 'Ivic', '0246096864', 120, NOW(), 'Strojarska cesta 20', 'Zagreb', 'Croatia', '10 000'),
                    ('Pero', 'Ivic', '0246053233', 80, NOW(), 'Strojarska cesta 5', 'Sesveta', 'Croatia', '10 100'),
                    ('Pero', 'Peric', '0246053234', 180, NOW(), 'Ulica kneza Domagoja', 'Zagreb', 'Croatia', '10 030');

insert into course (id, name, ects_points) values
                   (1, 'Web aplikacije u javi', 6),
                   (2, 'Programiranje u jeziku Java', 5),
                   (3, 'ASP.NET', 7);

insert into student_course (student_id, course_id) values
                           (1, 1),
                           (1, 2),
                           (2, 1),
                           (2, 2),
                           (2, 3),
                           (3, 3),
                           (3, 2);