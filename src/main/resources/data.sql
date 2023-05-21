INSERT INTO
    app_user (id, username, password, first_name, last_name)
VALUES
    (
        1,
        'admin',
        '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy',
        'admin',
        'admin'
    );

INSERT INTO
    app_user (id, username, password, first_name, last_name)
VALUES
    (
        2,
        'user',
        '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy',
        'user',
        'user'
    );

INSERT INTO
    authority (id, name)
VALUES
    (1, 'ROLE_ADMIN');

INSERT INTO
    authority (id, name)
VALUES
    (2, 'ROLE_USER');

INSERT INTO
    app_user_authority (user_id, authority_id)
VALUES
    (1, 1);

INSERT INTO
    app_user_authority (user_id, authority_id)
VALUES
    (2, 2);

INSERT INTO
    student (
    id,
    first_name,
    last_name,
    uid,
    ects_points,
    date_of_birth
)
VALUES
    (1, 'Ivo', 'Ivic', '0246096864', 120, '1991-01-27'),
    (2, 'Pero', 'Ivic', '0246053233', 80, '1999-02-28'),
    (3, 'Pero', 'Peric', '0246053234', 180, '2005-01-01');

INSERT INTO
    course (id, name, ects_points)
VALUES
    (1, 'Web aplikacije u javi', 6),
    (2, 'Programiranje u jeziku Java', 5),
    (3, 'ASP.NET', 7);

INSERT INTO
    student_course (student_id, course_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 3);