
API_ONVERA

API_ONVERA es una API REST desarrollada para gestionar usuarios, películas, categorías (Adultos y Niños), perfiles y procesos de recuperación de contraseña. El objetivo es brindar un backend funcional que soporte una aplicación cliente orientada al consumo de contenido audiovisual

El proyecto permite: • Autenticación y login de usuarios • Gestión de películas • Clasificación por categorías (Niños / Adultos) • Gestión de perfiles asociados al usuario • Gestión de constraseñas

Base de datos con relaciones entre usuarios, películas, categorías y perfiles • usuarios 1–N perfiles • categorias 1–N peliculas • usuarios N–N peliculas (a través de favoritos)

Tablas principales: usuarios peliculas (adultos y niños) categorias perfil cambio de contraseña

Endpoints Principales Autenticación: • POST /api/register — Registrar usuario • POST /api/login — Iniciar sesión • Usuarios: • GET /usuarios/:id — Obtener información • PUT /usuarios/:id — Actualizar • Películas: • GET /peliculas — Listar • POST /peliculas — Crear

Cómo ejecutar el proyecto

Instalar dependencias: npm install

Configurar archivo .env (BD) Ejecutamos servidor: npm run dev

Tecnologías Node.js como motor de ejecución Express para Framework backend PostgreSQL para Base de datos relacional JWT Autenticación Bcrypt para Hash de contraseñas
