# 🏦 Gestion de Comptes Bancaires - TP8

![Image](https://github.com/user-attachments/assets/77f05eb9-84db-46e0-a992-aed8186a2742)
![Image](https://github.com/user-attachments/assets/8e5ad638-fac7-4347-b77a-4b91837cefbc)
![Image](https://github.com/user-attachments/assets/3a94acac-7a39-43c8-9f22-bfeef441030f)

## 📋 Description
Une application Spring Boot pour la gestion de comptes bancaires avec une interface RESTful API.

## 🚀 Fonctionnalités
- ✅ Création et gestion de comptes bancaires
- 🔄 Opérations de dépôt et de retrait
- 📊 Consultation des soldes
- 🗃️ Base de données H2 intégrée
- 🌐 API RESTful

## 🛠️ Prérequis
- Java 8 ou supérieur
- Maven 3.6.3 ou supérieur
- Docker (optionnel)

## 🚀 Installation

### Configuration de la base de données
```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
```

### Avec Maven
```bash
mvn clean install
mvn spring-boot:run
```

### Avec Docker
```bash
docker-compose up -d
```

## 🌐 Accès
- Application : http://localhost:8082
- Console H2 : http://localhost:8082/h2-console
  - URL JDBC : `jdbc:h2:mem:testdb`
  - User : `sa`
  - Password : (laissez vide)

## 📚 Structure du Projet
```
src/
├── main/
│   ├── java/
│   │   └── com/example/tp8/
│   │       ├── controller/    # Contrôleurs REST
│   │       ├── model/         # Entités JPA
│   │       ├── repository/    # Repositories Spring Data
│   │       └── Tp8Application.java
│   └── resources/
│       └── application.properties
└── test/                     # Tests unitaires et d'intégration
```

## 📝 API Endpoints
- `GET /api/comptes` - Liste tous les comptes
- `GET /api/comptes/{id}` - Affiche un compte spécifique
- `POST /api/comptes` - Crée un nouveau compte
- `PUT /api/comptes/{id}` - Met à jour un compte
- `DELETE /api/comptes/{id}` - Supprime un compte

## 🤝 Contribution
1. Forkez le projet
2. Créez votre branche (`git checkout -b feature/AmazingFeature`)
3. Committez vos modifications (`git commit -m 'Add some AmazingFeature'`)
4. Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📄 Licence
Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 🙏 Remerciements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [H2 Database](https://www.h2database.com/)
- [Maven](https://maven.apache.org/)
