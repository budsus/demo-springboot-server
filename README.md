# demo-springboot-server
A simple project in order to provide web service RESTful demo.

## Setup Lingkungan
Untuk dapat menjalankan contoh aplikasi RESTful ini, terdapat perangkat yang harus dipasang:
* Spring Tool Suite - STS (dapat diunduh di Spring.io)
* JDK 1.8.x

Untuk mengambil code dari demo ini, dapat menggunakan tool dari STS atau menggunakan SourceTree atau Git tool suite lainnya. Syarat untuk dapat mengambil code ini adalah harus menjadi member dari Group Rawat Jalan RS Panti Rapih terlebih dahulu.

Pada lingkungan STS, silahkan di Import. Oleh karena menggunakan maven, file pom.xml menjadi penting. Semua konfigurasi koneksi ke database dapat di atur di src/main/resources/application.properties. Buatlah database, grant permission, dan buat tabel baru (dalam contoh ini digunakan MariaDB):

```
CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `registrasi_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
```

```
CREATE TABLE `phones` (
  `id` int(11) NOT NULL,
  `phonenumber` varchar(12) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `phones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKwv3t1162yqg75mx1xb9bcycs` (`person_id`);
ALTER TABLE `phones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `phones`
  ADD CONSTRAINT `FKwv3t1162yqg75mx1xb9bcycs` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);
```

```
CREATE TABLE `registrasi` (
  `id` int(11) NOT NULL,
  `noregis` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `registrasi`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `registrasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
```

Untuk setup koneksi ke database, konfigurasi dapat diatur pada file ```application-dev.properties``` dan ```application-release.properties``` yang terletak pada folder ```src/main/resources```.

## Informasi Tambahan
Sebelum menjalankan, jika ingin mengaktifkan debug mode, klik kanan item project demo, lalu Run As > Run Configuration, lalu pada tab Arguments, tambahkan --debug pada item Program arguments. Dan untuk menjalankan, silahkan klik kanan item project demo, lalu Run As > Spring Boot App.
