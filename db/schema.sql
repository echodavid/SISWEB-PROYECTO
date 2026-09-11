CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    rol VARCHAR(30) NOT NULL CHECK (rol IN ('CIUDADANO', 'OPERADOR', 'RESPONSABLE'))
);

CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS estados (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS reportes (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id),
    categoria_id INTEGER NOT NULL REFERENCES categorias(id),
    estado_id INTEGER NOT NULL REFERENCES estados(id),
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    ubicacion VARCHAR(200) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    prioridad VARCHAR(10) NOT NULL CHECK (prioridad IN ('BAJA', 'MEDIA', 'ALTA'))
);

CREATE TABLE IF NOT EXISTS evidencias (
    id SERIAL PRIMARY KEY,
    reporte_id INTEGER NOT NULL REFERENCES reportes(id) ON DELETE CASCADE,
    nombre_archivo VARCHAR(150) NOT NULL,
    tipo_archivo VARCHAR(50) NOT NULL,
    url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS comentarios (
    id SERIAL PRIMARY KEY,
    reporte_id INTEGER NOT NULL REFERENCES reportes(id) ON DELETE CASCADE,
    autor VARCHAR(100) NOT NULL,
    texto TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios (nombre, email, rol) VALUES
('Ana López', 'ana@demo.com', 'CIUDADANO'),
('Luis García', 'luis@demo.com', 'OPERADOR'),
('Marta Ruiz', 'marta@demo.com', 'RESPONSABLE')
ON CONFLICT (email) DO NOTHING;

INSERT INTO categorias (nombre, descripcion) VALUES
('SERVICIOS_PUBLICOS', 'Problemas de alumbrado, agua, drenaje y limpieza urbana'),
('SEGURIDAD', 'Riesgos, vigilancia o situaciones de inseguridad'),
('INFRAESTRUCTURA', 'Daños en vialidades, monumentos o servicios comunitarios')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO estados (nombre, descripcion) VALUES
('RECIBIDO', 'Reporte registrado y pendiente de revisión'),
('EN_PROCESO', 'El caso está siendo atendido'),
('RESUELTO', 'El problema fue atendido y verificado')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO reportes (usuario_id, categoria_id, estado_id, titulo, descripcion, ubicacion, prioridad) VALUES
(1, 1, 1, 'Fuga de agua en la avenida principal', 'Hay una fuga visible en la esquina y se observa pérdida de agua constante.', 'Colonia Centro, bloque 3', 'ALTA'),
(1, 2, 2, 'Poste de luz dañado', 'El poste presenta riesgo por caída y falta de iluminación en la zona.', 'Barrio San José, calle 7', 'MEDIA')
ON CONFLICT DO NOTHING;

INSERT INTO evidencias (reporte_id, nombre_archivo, tipo_archivo, url) VALUES
(1, 'fuga-avenida.jpg', 'IMAGE', 'https://example.com/evidencia/fuga-avenida.jpg'),
(2, 'poste-luz.png', 'IMAGE', 'https://example.com/evidencia/poste-luz.png')
ON CONFLICT DO NOTHING;

INSERT INTO comentarios (reporte_id, autor, texto) VALUES
(1, 'Operador', 'Se levantó la prioridad por riesgo de servicio.'),
(2, 'Responsable', 'Se programó revisión técnica del poste.')
ON CONFLICT DO NOTHING;
