-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 12, 2021 lúc 06:30 PM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopfone`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_items`
--

CREATE TABLE `cart_items` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `custumer_id` int(11) DEFAULT NULL,
  `confirm` tinyint(4) DEFAULT 0,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cart_items`
--

INSERT INTO `cart_items` (`id`, `product_id`, `custumer_id`, `confirm`, `quantity`) VALUES
(10000, 1, 2, 0, 1),
(10001, 3, 2, 0, 1),
(10002, 8, 2, 0, 1),
(10003, 15, 2, 0, 1),
(10004, 6, 2, 0, 0),
(10005, 4, 2, 0, 0),
(10006, 2, 2, 0, 0),
(10043, 6, 7, 0, 0),
(10044, 16, 7, 0, 0),
(10045, 15, 7, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `catelories`
--

CREATE TABLE `catelories` (
  `id` int(11) NOT NULL,
  `enabled` tinyint(4) DEFAULT 1,
  `name_catelogry` varchar(128) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `catelories`
--

INSERT INTO `catelories` (`id`, `enabled`, `name_catelogry`, `parent_id`) VALUES
(1, 1, 'product', 1),
(2, 1, 'profile', 2),
(3, 1, 'detailproduct', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `custumer`
--

CREATE TABLE `custumer` (
  `id` int(11) NOT NULL,
  `email1` varchar(128) NOT NULL,
  `password1` varchar(128) NOT NULL,
  `hoten` varchar(128) DEFAULT NULL,
  `diachi1` varchar(128) DEFAULT NULL,
  `sdt1` varchar(15) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT current_timestamp(),
  `lasttime` timestamp NOT NULL DEFAULT current_timestamp(),
  `enabled` tinyint(4) DEFAULT 0,
  `auth_provider` varchar(15) DEFAULT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `TokenPW` varchar(128) DEFAULT NULL,
  `image` varchar(128) DEFAULT NULL,
  `parent_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `custumer`
--

INSERT INTO `custumer` (`id`, `email1`, `password1`, `hoten`, `diachi1`, `sdt1`, `createtime`, `lasttime`, `enabled`, `auth_provider`, `verification_code`, `TokenPW`, `image`, `parent_id`) VALUES
(2, 'jupiter@gmail.com', '$2a$10$EjHf.ecYDkwK5x.QTi/GJOKsVeAXEIOm3Wjmynx.yuZzoNSTIMqoa', 'Quân124', 'Tôn Đản', '12312313', '2021-05-24 14:07:45', '2021-05-24 14:07:45', 1, 'BASIC', NULL, NULL, NULL, 1),
(7, 'kamusuyurikun@gmail.com', '$2a$10$BGgGb3tKsePbJwUkA5bHc.BC0zM5WfLi7bMa/zib0seoQHaakIZtm', 'Nguyễn Phước', 'Hoàng QUốc Việt Q7', '0833665223', '2021-05-26 12:37:15', '2021-05-26 12:37:15', 1, 'BASIC', NULL, NULL, '123630219_3775541565798499_6141687691207080649_n.jpg', 1),
(17, 'nhockhovuivela9@yahoo.com.vn', '$2a$10$Rr0JUJU9Aj9tsMMos/9f0eShWBhfXXyec60bHYLsCYKs8UjKDv5ni', 'Thế Dân', '', '', '2021-06-05 18:58:34', '2021-06-05 18:58:34', 1, 'FACEBOOK', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `images_product`
--

CREATE TABLE `images_product` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `image` varchar(128) NOT NULL,
  `product_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `images_product`
--

INSERT INTO `images_product` (`id`, `product_id`, `image`, `product_code`) VALUES
(1, 1, 'GalaxyNote10Plus.jpg', 111),
(2, 1, '-samsung-galaxy-note-10-plus-thaotac.jpg', 111),
(3, 1, '-samsung-galaxy-note-10-plus-thumbvideo1.jpg', 111),
(4, 1, 'vi-vn-samsung-galaxy-note-10-plus-chuyenchu.jpg', 111),
(5, 1, '-vi-vn-samsung-galaxy-note-10-plus-tinhnang-copy.jpg', 111),
(25, 3, 'vi-vn-samsung-galaxy-s10-plus-bixby.jpg', 113),
(26, NULL, 'vi-vn-samsung-galaxy-s10-plus-bixbyvision.jpg', 113),
(27, NULL, 'vi-vn-samsung-galaxy-s10-plus-bonho.jpg', 113),
(28, NULL, 'vi-vn-samsung-galaxy-s10-plus-manhinh.jpg', 113),
(29, NULL, 'vi-vn-samsung-galaxy-s10-plus-tienich.jpg', 113),
(30, NULL, 'iphone-11-didongviet.jpg', 114),
(31, NULL, 'iphone-11-do-dai-dien-19367f.jpg', 114),
(32, NULL, 'iphone-11-128gb-tgdd5-1.jpg', 114),
(33, NULL, '6gwcJ32LuV58_anh-1.jpg', 114);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `manufact`
--

CREATE TABLE `manufact` (
  `id` int(11) NOT NULL,
  `manufacturer` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `manufact`
--

INSERT INTO `manufact` (`id`, `manufacturer`) VALUES
(1, 'IPhone'),
(2, 'SAMSUNG'),
(3, 'OPPO');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `id_manu` int(11) NOT NULL,
  `id_theloai` int(11) NOT NULL,
  `name_` varchar(100) NOT NULL,
  `image` varchar(64) DEFAULT NULL,
  `date_create` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `date_update` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `best_sell` tinyint(4) DEFAULT 0,
  `new_product` tinyint(4) DEFAULT 0,
  `price` double NOT NULL,
  `price_sell` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT 2,
  `keyword` varchar(128) NOT NULL,
  `description` text DEFAULT NULL,
  `more_details` text DEFAULT NULL,
  `product_code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `id_manu`, `id_theloai`, `name_`, `image`, `date_create`, `date_update`, `best_sell`, `new_product`, `price`, `price_sell`, `quantity`, `parent_id`, `keyword`, `description`, `more_details`, `product_code`) VALUES
(1, 1, 1, 'Galaxy Note10', 'GalaxyNote10Plus.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 18990000, 0, 50, 2, 'Galaxy-Note10', 'Nếu như từ trước tới nay dòng Galaxy Note của Samsung thường ít được các bạn nữ sử dụng bởi kích thước màn hình khá lớn khiến việc cầm nắm trở nên khó khăn thì Samsung Galaxy Note 10 sẽ là chiếc smartphone nhỏ gọn, phù hợp với cả những bạn có bàn tay nhỏ', 'Với hàng loạt những siêu phẩm từng tung ra thị trường, điện thoại Samsung lại một lần nữa làm cộng đồng người yêu công nghệ phải đứng ngồi không yên khi trình làng bộ đôi Samsung Galaxy Note 10 và Note 10 Plus mang đến những trải nghiệm tuyệt đỉnh như máy tính ngay từ chính chiếc smartphone của bạn, giúp bạn có thể làm mọi thứ chỉ bằng một chiếc điện thoại. Ngoài ra, bạn cũng có thể tham khảo phiên bản rút gọn với giá bán phải chăng hơn là Samsung Note 10 Plus.Mặc dù Samsung Galaxy Note 20 sắp được ra mắt nhưng hiện tại Note 10 vẫn đang là sự lựa chọn hợp lý với giá tốt đi kèm nhiều tính năng cao cấp.Samsung Galaxy Note 10 mang phong cách thiết kế vuông vắn, kích thước 71.8 x 151 mm và độ mỏng chỉ 7.9mm. Với khung điện thoại được làm từ chất liệu thép không gỉ kết hợp với thủy tinh cao cấp được đánh bóng cho khả năng bắt sáng tạo nên ánh quang cùng lớp kính cường lực Gorilla 6 mang đến sự bảo vệ vừng chắc cho Samsung Galaxy Note. Các chi tiết, đường nét được gia công tinh tế, tỉ mỉ mang đến sự hoàn hảo cho vẻ ngoài của chiếc smartphone siêu phẩm này. Khả năng kháng bụi, kháng nước chuẩn IP68 mang đến sự bền bỉ, bảo vệ điện thoại khỏi các hư hại do bụi và nước gây ra.Với sự trở lại lần này, Samsung mang đến cho cộng đồng fan và những người yêu công nghệ 3 phiên bản màu của Samsung Galaxy Note 10 đó là màu Ánh Cực Quang, Đen Pha Lê và Hồng Ruby cho bạn có nhiều sự lụa chọn phù hợp với phong cách của bản thân. Đặc biệt ở phiên bản màu Ánh Cực Quang, bạn sẽ nhìn thấy sự chuyển màu theo góc độ ánh sáng mang đến sự mới lạ và hợp xu hướng hiện đại.Màn hình không viền kích thước lớn 6.3 inch với tấm nền Dynamic AMOLED mở ra không gian hiển thị chuẩn điện ảnh và cực kỳ sống động, màu sắc tươi sáng ở tất cả các sắc độ và luôn được tối ưu độ thoải mái dành cho mắt thường, giúp bạn xem những bộ phim hấp dẫn, chơi game,…một cách tuyệt vời nhất dù đang ở bất cứ điều kiện ánh sáng nào. Với công nghệ điều chỉnh dải màu sắc chuẩn HDR 10+ cho bạn đắm chìm vào không gian hiển thị hoàn hảo.Giờ đây, việc bảo vệ mắt người dùng cũng được Samsung quan tâm khi giảm tối đa mức phát ánh sáng xanh giúp việc sử dụng smartphone lâu dài không còn gây mỏi mắt, giảm thiểu sự gây hại lên mắt người dùng mà vẫn giữ màu sắc phong phú bao phủ 100%.', 111),
(2, 1, 1, 'Galaxy Note10 Plus', 'GalaxyNote10Plus.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 19990000, 0, 50, 2, 'Galaxy-Note10-Plus', 'Nếu như từ trước tới nay dòng Galaxy Note của Samsung thường ít được các bạn nữ sử dụng bởi kích thước màn hình khá lớn khiến việc cầm nắm trở nên khó khăn thì Samsung Galaxy Note 10 sẽ là chiếc smartphone nhỏ gọn, phù hợp với cả những bạn có bàn tay nhỏ', NULL, 112),
(3, 1, 1, 'Galaxy S10 Plus 128Gb', 'GalaxyS10Plus128Gb.png', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 11990000, 0, 50, 2, 'Galaxy-S10-Plus-128Gb', NULL, NULL, 113),
(4, 2, 2, 'iPhone 11 128Gb Quốc tế', 'iPhone 11128Gb.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 15390000, 0, 50, 2, 'iPhone-11-128Gb-Quốc-tế', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 114),
(5, 2, 2, 'iPhone 11 Pro 256Gb Quốc tế', 'iPhone 11Pro256Gb.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 15390000, 0, 50, 2, 'iPhone-11-Pro-256Gb-Quốc-tế', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 115),
(6, 2, 2, 'iPhone 12 Pro Max 512Gb Quốc tế', 'iPhone 12ProMax.png', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 34990000, 0, 50, 2, 'iPhone-12-Pro-Max-512Gb-Quốc-tế', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 116),
(7, 2, 2, ' iPhone 12 Pro Max 256GB', 'iPhone 11128Gb.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 35490000, 0, 50, 2, 'iPhone-12-Pro-Max-256GB', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 117),
(8, 2, 2, ' iPhone 12 Pro 128GB', 'iPhone 11128Gb.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 28490000, 0, 50, 2, 'iPhone-12-Pro-128GB', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 118),
(9, 1, 1, 'Samsung Galaxy M51', 'SamsungGalaxyM51.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 8490000, 0, 50, 2, 'Samsung-Galaxy-M51', NULL, NULL, 119),
(10, 2, 2, 'iPhone 12 mini 64GB ', 'iPhone12mini 64GB.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 16990000, 0, 50, 2, 'iPhone-12-mini-64GB ', 'Đảm bảo hàng chính hãng, Đền 10 lần giá SP nếu hàng nhái, hàng giả', NULL, 120),
(11, 1, 1, 'Samsung Galaxy S21 5G ', 'SamsungGalaxyS215G.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 17990000, 0, 50, 2, 'Samsung-Galaxy-S21-5G ', NULL, NULL, 121),
(12, 1, 1, 'Samsung Galaxy A02', 'SamsungGalaxyA02.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 1990000, 0, 50, 2, 'Samsung-Galaxy-A02', NULL, NULL, 122),
(13, 1, 1, 'Samsung Galaxy A12 (6GB/128GB)', 'SamsungGalaxyA12.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 4390000, 0, 50, 2, 'Samsung-Galaxy-A12-(6GB/128GB)', NULL, NULL, 123),
(14, 1, 1, 'Samsung Galaxy Z Fold2 5G ', 'SamsungGalaxyZFold25G.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 50000000, 0, 50, 2, 'Samsung-Galaxy-Z-Fold2-5G ', NULL, NULL, 124),
(15, 1, 1, 'Samsung Galaxy S21 Ultra 5G 256GB', 'SamsungGalaxyS21Ultra5G.png', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 0, 1, 28990000, 0, 50, 2, 'Samsung-Galaxy-S21-Ultra-5G-256GB', NULL, NULL, 125),
(16, 1, 1, 'Samsung Galaxy Note 20 Ultra 5G Trắng', 'SamsungGalaxyNote.jpg', '2021-07-01 21:39:28', '2021-07-01 21:39:28', 1, 0, 23990000, 0, 50, 2, 'Samsung Galaxy-Note-20-Ultra-5G-Trắng', NULL, NULL, 126),
(17, 1, 1, 'Samsung Galaxy Note 20', 'SamsungGalaxyNote.jpg', '2021-06-25 16:35:34', '2021-06-25 16:35:34', 0, 0, 15990000, 0, 50, 2, 'Samsung-Galaxy-Note-20', NULL, NULL, 127),
(18, 1, 1, 'Samsung Galaxy S20 FE (8GB/256GB)', 'SamsungGalaxyS20FE.png', '2021-06-25 16:35:34', '2021-06-25 16:35:34', 0, 0, 15450000, 0, 50, 2, 'Samsung Galaxy-S20-FE-(8GB/256GB)', NULL, NULL, 128),
(19, 1, 1, 'Samsung Galaxy A72 ', 'SamsungGalaxyA72.jpg', '2021-06-25 16:35:34', '2021-06-25 16:35:34', 0, 0, 11490000, 0, 50, 2, 'Samsung-Galaxy-A72 ', NULL, NULL, 129),
(20, 1, 1, 'Samsung Galaxy A52 5G ', 'SamsungGalaxyA525G.jpg', '2021-06-25 16:35:34', '2021-06-25 16:35:34', 0, 0, 10990000, 0, 50, 2, 'Samsung-Galaxy-A52-5G ', NULL, NULL, 130);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `profile_user`
--

CREATE TABLE `profile_user` (
  `id` int(11) NOT NULL,
  `fullname` varchar(64) NOT NULL,
  `address` varchar(128) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `date_start_toWork` timestamp NOT NULL DEFAULT current_timestamp(),
  `image` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role_1`
--

CREATE TABLE `role_1` (
  `idrole` int(11) NOT NULL,
  `role_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role_1`
--

INSERT INTO `role_1` (`idrole`, `role_name`) VALUES
(1, 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `id` int(11) NOT NULL,
  `operating_system` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`id`, `operating_system`) VALUES
(1, 'IOS'),
(2, 'Android');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_`
--

CREATE TABLE `user_` (
  `id` int(11) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password_` varchar(128) NOT NULL,
  `enabled` tinyint(4) DEFAULT 1,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_`
--

INSERT INTO `user_` (`id`, `username`, `password_`, `enabled`, `email`) VALUES
(1, 'yukumari@gmail.com', '$2a$10$j7JgCunR94IQWQEzN/jgzed392oN2zNutDt7YnRB/78U9MVju0qqC', 1, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `idrole` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `custumer_id` (`custumer_id`);

--
-- Chỉ mục cho bảng `catelories`
--
ALTER TABLE `catelories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Chỉ mục cho bảng `custumer`
--
ALTER TABLE `custumer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Chỉ mục cho bảng `images_product`
--
ALTER TABLE `images_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `manufact`
--
ALTER TABLE `manufact`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`,`id_manu`,`id_theloai`),
  ADD KEY `id_theloai` (`id_theloai`),
  ADD KEY `id_manu` (`id_manu`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Chỉ mục cho bảng `profile_user`
--
ALTER TABLE `profile_user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `role_1`
--
ALTER TABLE `role_1`
  ADD PRIMARY KEY (`idrole`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_`
--
ALTER TABLE `user_`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`,`idrole`),
  ADD KEY `idrole` (`idrole`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000;

--
-- AUTO_INCREMENT cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10046;

--
-- AUTO_INCREMENT cho bảng `catelories`
--
ALTER TABLE `catelories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `custumer`
--
ALTER TABLE `custumer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `images_product`
--
ALTER TABLE `images_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT cho bảng `manufact`
--
ALTER TABLE `manufact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `profile_user`
--
ALTER TABLE `profile_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `theloai`
--
ALTER TABLE `theloai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user_`
--
ALTER TABLE `user_`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`custumer_id`) REFERENCES `custumer` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_4` FOREIGN KEY (`custumer_id`) REFERENCES `custumer` (`id`);

--
-- Các ràng buộc cho bảng `catelories`
--
ALTER TABLE `catelories`
  ADD CONSTRAINT `catelories_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `catelories` (`id`);

--
-- Các ràng buộc cho bảng `custumer`
--
ALTER TABLE `custumer`
  ADD CONSTRAINT `custumer_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `catelories` (`parent_id`),
  ADD CONSTRAINT `custumer_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `catelories` (`parent_id`);

--
-- Các ràng buộc cho bảng `images_product`
--
ALTER TABLE `images_product`
  ADD CONSTRAINT `images_product_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_theloai`) REFERENCES `theloai` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`id_manu`) REFERENCES `manufact` (`id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `catelories` (`parent_id`),
  ADD CONSTRAINT `products_ibfk_4` FOREIGN KEY (`parent_id`) REFERENCES `catelories` (`parent_id`);

--
-- Các ràng buộc cho bảng `profile_user`
--
ALTER TABLE `profile_user`
  ADD CONSTRAINT `profile_user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user_` (`id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user_` (`id`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`idrole`) REFERENCES `role_1` (`idrole`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
