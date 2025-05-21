# 🚗 Vehicle Rental System

Araç kiralama işlemlerini yöneten basit bir Java uygulamasıdır. Müşteri, araç ve kiralama işlemleri yönetilebilir; ayrıca kullanıcı rolleri ve sepet sistemi gibi özellikler de desteklenmektedir.

---

## 📌 Özellikler

- 👤 Müşteri kaydı ve girişi (bireysel / kurumsal)
- 🚗 Araç listeleme, arama ve filtreleme (kategoriye göre)
- 🛒 Sepete araç ekleme, görüntüleme ve temizleme
- 📦 Sepetteki araçları kiralama ve ödeme işlemi
- 💳 Ödeme yöntemi seçimi
- 📜 Kiralama geçmişini görüntüleme
- 🔐 Kullanıcı rolleri (admin / müşteri)
- 📆 Kiralama süreleri: saatlik, günlük, haftalık, aylık

---

## 🛠️ Kullanılan Teknolojiler

- **Java 17+**
- **PostgreSQL**
- **JDBC**
- **Maven**
- Katmanlı mimari: DAO, Service, Model, Utility sınıfları

---

## 🗂️ Veritabanı Tabloları

- `users`: Sistem kullanıcıları (admin/müşteri)
- `customer`: Müşteri bilgileri
- `vehicle`: Kiralanabilir araçlar
- `category`: Araç kategorileri (otomobil, motosiklet vs.)
- `cart`, `cart_items`: Sepet ve sepet öğeleri
- `rent`, `rent_item`: Kiralama işlemleri ve kiralanan araçlar
- `payment`: Ödeme kayıtları

---

## ⚙️ Kurulum

1. PostgreSQL'de gerekli tabloları oluştur:
   - SQL scriptleri `resources/sql` veya `src/main/resources` klasörüne eklenebilir.
2. `DBUtil.java` içindeki bağlantı ayarlarını kendi veritabanına göre güncelle:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/arac_kiralama";
private static final String USER = "postgres";
private static final String PASSWORD = "postgres";
