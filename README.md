# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

Melakukan *upgrade* Website Marketplace sederhana pada Tugas 1 dengan mengaplikasikan **arsitektur web service REST dan SOAP**.

# SaleProject
Easy-buy~Easy-sell Online Store
------
Di era digital ini, online store sudah merajarela ada dimana-mana. Masyarakat dapat membeli barang dari rumah dengan hanya bermodalkan komputer atau smartphone. Para penjual dapat melakukan bisnis online store dengan mudah, cukup dengan foto, menambahkan deskripsi kemudian upload.

Namun, biasanya pada online store pembeli dan penjual memiliki tipe akun yang berbeda. Jika seorang penjual ingin membeli barang, dia harus logout akun penjual, kemudian login ke akun pembeli. Artinya dia harus punya 2 akun.

Hal tersebut tidak perlu dilakukan pada **SaleProject**. Pembeli dan penjual hanya perlu memiliki satu akun. Pengguna yang sudah memiliki akun dapat melakukan pembelian dan penjualan dengan sangat mudah.

Setelah sukses dengan Easy-buy~Easy-sell V-01, Website kami kembali dengan versi mutakhir V-02 dengan tampilan yang samasekali tidak berubah namun dengan kecepatan yang lebih rendah karena menggunakan backend JSP.

### Penjelasan
#### Basis Data Pada Sistem
Sistem ini menggunakan 2 buah database, yaitu saleprojectaccount dan saleprojectmarket. saleprojectaccount menyimpan informasi yang diperlukan untuk login & register. saleproject market menyimpan informasi produk dan penjualan, sekaligus juga menyimpan like dan informasi user dalam tabel like dan user. 

#### Konsep *shared session* dengan menggunakan REST


#### Pembangkitan token dan expire time pada sistem yang anda buat.
Sistem kami melakukan generasi token di saat login. Pada identity service di login, token digenerate oleh fungsi bawaan SecureRandom dari java.

#### Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini
Kelemahan dari arsitektur aplikasi ini adalah membutuhkan database yang redundan karena menggunakan 2 protokol (web service dan identity service).


### Pembagian Tugas

REST :
1. Generate token : 135130XX
2. Validasi token : 135130XX
3. Fungsionaltiax X : 135130XX
4. Login : 13514108
5. Register : 13514047
6. Logout : 13514012

SOAP :

1. Catalog : 13514108
2. Add Product : 13514012
3. Edit Product : 13514012
4. Your Products : 13514012
5. Sales : 13514048
6. Purchase : 13514048
7. Confirmation Purchase : 13514048, 13514108

Web app (JSP) :

1. Login : 13514108
2. Register : 13514048
3. Catalog : 13514108
4. Add Product : 13514012
5. Edit Product : 13514012
6. Your Products : 13514012
7. Sales : 13514048
8. Purchase : 13514048
9. Confirmation Purchase : 13514048, 13514108

## About
### Team ChickenSaitamaZone

Mahasiswa S1 Prodi Teknik Informatika Institut Teknologi Bandung
Joshua Aditya Kosasih - 13514012 - Betatestah
Nikolas Wangsaputra - 13514048 - SimpliCty
Michael -13514108 - Viniel