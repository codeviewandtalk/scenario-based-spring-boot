//Insert authors in the author table

INSERT INTO author (id,name) VALUES
  (1,'George Orwell'),
  (2,'J.K. Rowling'),
  (3,'J.R.R. Tolkien'),
  (5,'Agatha Christie'),
  (6,'Jane Austen');


//Insert Book in the book Table

  INSERT INTO book (id,title, author_id) VALUES
  (1,'1984', 1),
  (2,'Animal Farm', 1),
  (3,'Harry Potter and the Sorcerer', 2),
  (4,'Harry Potter and the Chamber of Secrets', 2),
  (5,'The Hobbit', 3),
  (6,'The Lord of the Rings', 3),
  (7,'Murder on the Orient Express', 6),
  (8,'And Then There Were None', 6),
  (9,'Pride and Prejudice', 5),
  (10,'Sense and Sensibility', 5);