# Lab 04 — JUnit тест

**Оюутны нэр:** Т.Мөнхцэцэн
**Оюутны код:** B232270025

## 1. Орчин

### Java version

```text
Java version: 17.0.20.1
Vendor: Ubuntu
Runtime: /usr/lib/jvm/java-17-openjdk-amd64
```

### Maven version

```text
Apache Maven 3.9.12
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.18.33.2-microsoft-standard-WSL2", arch: "amd64", family: "unix"
```

## 2. GradeCalculatorTest

`GradeCalculatorTest` нь нийт **11 test method**-той:

- **9** энгийн `@Test` method
- **2** `@ParameterizedTest` method

Parameterized test-үүд олон input дээр ажилладаг тул нийт **23 test invocation** ажилласан.

`results/mvn-test.txt` үр дүн:

```text
Tests run: 23, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Эдгээр тестүүдээр дараах үйлдлүүдийг шалгасан:

- 90 оноо A болох
- 89.99 оноо B болох
- 60 оноо D болох
- 59.99 оноо F болох
- 100 оноо A болох
- 0–100 хязгаараас гарсан оноонд exception үүсэх
- Нийт оноог зөв тооцоолох
- Нийт онооны component-уудын зөвшөөрөгдөх хязгаарыг шалгах
- Олон төрлийн input ашиглан `letterGrade()` болон `totalScore()`-г шалгах

## 3. Mutation тест

Mutation testing хийхдээ `GradeCalculator.java`-ийн дараах нөхцөлийг өөрчилсөн:

```java
if (score >= 90)
```

гэсэн нөхцөлийг түр хугацаанд:

```java
if (score > 90)
```

болгосон.

Энэ өөрчлөлтийн дараа `results/mvn-test-mutant.txt` дээр:

```text
Tests run: 23, Failures: 2, Errors: 0, Skipped: 0
BUILD FAILURE
```

гэсэн үр дүн гарсан.

Алдаа гарсан тестүүд:

```text
GradeCalculatorTest.ninetyIsExactlyA
GradeCalculatorTest.letterGradeBoundaries(double, String)[2]
```

Хоёр тестийн аль алинд нь:

```text
expected: <A> but was: <B>
```

гэсэн алдаа гарсан.

Энэ нь mutation-ийг тестүүд илрүүлж чадсан гэсэн үг. Ялангуяа **90 оноо A байх ёстой** гэсэн boundary condition-ийг тестүүд хамгаалж байна. Mutation testing дууссаны дараа эх кодыг буцааж:

```java
if (score >= 90)
```

болгож сэргээсэн.

## 4. Хамгийн сонирхолтой илэрсэн алдаа

Хамгийн сонирхолтой илэрсэн алдаа нь **90 онооны заагтай** холбоотой байсан. Анхны кодод 90 болон түүнээс дээш оноог A гэж зөв тооцож байсан. Mutation testing хийхдээ `>= 90` нөхцөлийг `> 90` болгон өөрчлөхөд яг 90 оноо B болж буруу тооцогдсон. `ninetyIsExactlyA()` тест энэ алдааг шууд илрүүлсэн. Мөн `letterGradeBoundaries` параметртэй тестийн 90 онооны тохиолдол мөн алдааг илрүүлсэн. Энэ нь онооны зааг дээрх утгуудыг тусгайлан шалгах нь жижигхэн кодын өөрчлөлтөөс үүссэн алдааг илрүүлэхэд чухал болохыг харуулсан. 89.99, 90, 100 зэрэг заагийн утгуудыг шалгаснаар дүн буруу тооцогдохоос сэргийлж чадсан. Тиймээс зөвхөн дундаж утгуудыг шалгах биш, зааг дээрх утгуудыг мөн заавал шалгах хэрэгтэй гэдгийг ойлгосон.

## 5. AI ашиглан үүсгэсэн тестүүд

AI ашиглан `GradeCalculator` class-д зориулсан нэмэлт тестүүдийг үүсгэсэн.

AI тестийн class:

```text
src/test/java/mn/edu/must/sqat/AIGradeCalculatorTest.java
```

`AIGradeCalculatorTest` нь:

- **9** энгийн `@Test` method
- **2** `@ParameterizedTest` method
- Нийт **11 test methods**
- Нийт **29 test invocations**

агуулсан.

AI тестийн үр дүн:

```text
Tests run: 29, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

AI-аар үүсгэсэн тестүүд нь A-гаас F хүртэлх бүх дүнгийн нөхцөл, буруу оноо, `totalScore()`-ийн хэсэг бүрийн зөвшөөрөгдөх хязгаар болон заагийн утгуудыг шалгасан. Ялангуяа `90`, `89.99`, `80`, `79.99`, `70`, `69.99`, `60`, `59.99` зэрэг заагийн утгуудыг нэг параметртэй тестээр олон тохиолдлоор шалгасан нь хэрэгтэй байсан. Мөн ирц, лаборатори, сорил 1, сорил 2, шалгалтын оноо тус бүр зөвшөөрөгдөх хэмжээнээс бага болон их үед алдаа гаргах эсэхийг шалгасан. Зарим тест нь өмнө бичсэн тестүүдтэй ижил үйлдлийг шалгаж байсан боловч параметртэй тест ашигласнаар ижил бүтэцтэй олон тохиолдлыг бага хэмжээний кодоор шалгах боломжтой болсон. Миний бичсэн тестүүдтэй харьцуулахад AI нь `totalScore()`-ийн хэсэг тус бүрийн буруу утгыг илүү олон тохиолдлоор шалгасан. Харин миний бичсэн тестүүдэд 90 оноо яг A байх зэрэг чухал заагийн утгуудыг тусдаа тестээр тодорхой шалгасан.

## 6. Test файлууд

```text
Тестлэгдэх класс
lab04-junit/src/main/java/mn/edu/must/sqat/GradeCalculator.java
Тестүүд
lab04-junit/src/test/java/mn/edu/must/sqat/GradeCalculatorTest.java
lab04-junit/src/test/java/mn/edu/must/sqat/AIGradeCalculatorTest.java
```

Тестийн үр дүн:

```text
results/
├── mvn-test.txt
├── mvn-test-mutant.txt
└── ai-test.txt
```

## 7. Дүгнэлт

Энэ лабораторийн ажлаар JUnit 5 ашиглан нэгжийн тест болон олон тохиолдолтой тестүүд бичиж, Maven ашиглан тестүүдийг ажиллуулсан. `GradeCalculatorTest` нь 11 тестийн аргатай бөгөөд нийт 23 удаа тест ажилласан. Mutation testing хийхдээ `>= 90` нөхцөлийг `> 90` болгон өөрчлөхөд хоёр тест алдааг илрүүлсэн. Энэ нь заагийн утгуудыг шалгах нь тестийн чухал хэсэг болохыг харуулсан. Мөн AI ашиглан нэмэлт тестүүд үүсгэж, тэдгээрийг шалгасны дараа нийт 29 тестийн тохиолдол бүгд амжилттай ажилласан.
