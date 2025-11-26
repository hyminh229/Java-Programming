-- =============================================
-- Script SQL để tạo database và bảng users
-- Database: tutorial
-- =============================================

-- Bước 1: Tạo database (nếu chưa tồn tại)
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'tutorial')
BEGIN
    CREATE DATABASE tutorial;
    PRINT 'Database "tutorial" đã được tạo thành công!';
END
ELSE
BEGIN
    PRINT 'Database "tutorial" đã tồn tại.';
END
GO

-- Bước 2: Sử dụng database tutorial
USE tutorial;
GO

-- Bước 3: Tạo bảng users (nếu chưa tồn tại)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'users' AND type = 'U')
BEGIN
    CREATE TABLE users (
        id NVARCHAR(50) PRIMARY KEY,
        first_name NVARCHAR(100) NOT NULL,
        last_name NVARCHAR(100) NOT NULL,
        mark NVARCHAR(50)
    );
    PRINT 'Bảng "users" đã được tạo thành công!';
END
ELSE
BEGIN
    PRINT 'Bảng "users" đã tồn tại.';
    -- Xóa dữ liệu cũ nếu muốn (tùy chọn)
    -- TRUNCATE TABLE users;
END
GO

-- Bước 4: Chèn dữ liệu mẫu (nếu chưa có)
IF NOT EXISTS (SELECT * FROM users WHERE id = '1')
BEGIN
    INSERT INTO users (id, first_name, last_name, mark) VALUES ('1', N'Lam', N'Nguyen', '7');
    PRINT 'Đã thêm user: Lam Nguyen';
END

IF NOT EXISTS (SELECT * FROM users WHERE id = '3')
BEGIN
    INSERT INTO users (id, first_name, last_name, mark) VALUES ('3', N'Van', N'Nguyen', '7');
    PRINT 'Đã thêm user: Van Nguyen';
END
GO

-- Bước 5: Kiểm tra dữ liệu
SELECT * FROM users;
GO

PRINT 'Hoàn thành! Database và bảng đã sẵn sàng.';
GO

