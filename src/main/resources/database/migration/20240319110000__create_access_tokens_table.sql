CREATE TABLE access_token (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  token VARCHAR(255) NOT NULL,
  expiry_date DATETIME,
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
