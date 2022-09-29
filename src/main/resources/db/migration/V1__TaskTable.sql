CREATE TABLE task (
    task_id UUID NOT NULL PRIMARY KEY,
    task_title TEXT NOT NULL,
    task_due_date_time TEXT NOT NULL,
    task_reminder_on BOOLEAN NOT NULL
);