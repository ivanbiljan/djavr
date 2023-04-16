insert into student (first_name, last_name, uid, ects_points, date_of_birth) values
                    ('Ivo', 'Ivic', '0246053232', 120, NOW()),
                    ('Pero', 'Ivic', '0246053233', 80, NOW()),
                    ('Pero', 'Peric', '0246053234', 180, NOW());

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