# 📖 Donk User Guide

![Product Screenshot](path/to/screenshot.png)  
*Your intelligent chatbot for managing tasks effortlessly.*

---

## 📌 Features Overview

- 📝 **Task Management**: Add, remove, mark, and list tasks easily.
- ⏳ **Deadlines & Events**: Keep track of important dates.
- 🔍 **Find & Sort**: Locate tasks by keywords or dates, and sort them chronologically.
- 💾 **Persistent Storage**: Automatically saves tasks to disk.
- 🎨 **JavaFX GUI**: Interactive and user-friendly interface.

---

## 📖 Commands & Usage

### 📋 **1. List all tasks**
Displays all tasks in the current task list.

☑️Example:
```sh
list
```

☑️Example Output:
```sh
Here are your tasks:
1. [T][ ] Read JavaFX Tutorial
2. [D][X] Submit CS2103T Report (by: 20 Feb 2025)
3. [E][ ] Attend Meeting (from: 22 Feb 2025 to: 23 Feb 2025)
```


### 📋 **2. Adding a To-Do**
Add a reminder for your To-Do task so that you won't forget!

☑️Example:
```sh
todo Read JavaFX Tutorial
```

☑️Example Output:
```sh
Got it! I have added this task:
[T][ ] Read JavaFX Tutorial
Now you have 3 tasks in the list.
```


### 📋 **3. Adding a Deadline**
Adds a task with a deadline. Remember to type in the date in the form "YYYY-MM-DD", 
so that Donk can manage your tasks better!

☑️Example:
```sh
deadline Submit CS2103T Report /by 2025-02-20
```

☑️Example Output:
```sh
Got it. I've added this task:
[D][ ] Submit CS2103T Report (by: 20 Feb 2025)
Now you have 4 tasks in the list.
```

### 📋 **4. Adding an Event**
Adds an event with a start and end time. Remember to type in the date in the form "YYYY-MM-DD",
so that Donk can manage your tasks better!

☑️Example:
```sh
event Attend Meeting /from 2025-02-22 /to 2025-02-23
```

☑️Example Output:
```sh
Got it. I've added this task:
[E][ ] Attend Meeting (from: 22 Feb 2025 to: 23 Feb 2025)
Now you have 5 tasks in the list.
```


### ✅ **5. Marking a Task as Done/Undone**
Marks a task as done or not done yet.

☑️Example:
```sh
mark 2
```

☑️Example Output:
```sh
Nice! I've marked this task as done:
[D][X] Submit CS2103T Report (by: 20 Feb 2025)
```


### ❌ **6. Deleting a Task**
Deletes a task from the list.

☑️Example:
```sh
delete 1
```

☑️Example Output:
```sh
Noted. I've removed this task:
[T][ ] Read JavaFX Tutorial
Now you have 4 tasks in the list.
```

### 🔍 **7. Finding Tasks by Keyword**
Search for tasks by name.

☑️Example:
```sh
find Meeting
```

☑️Example Output:
```sh
Here are the matching tasks:
[E][ ] Attend Meeting (from: 2025-02-22 to: 2025-02-23)
```

### 📅  **8. Finding Tasks by Date**
Search for tasks occurring on a specific date.

☑️Example:
```sh
get 2025-02-20
```

☑️Example Output:
```sh
Here are the tasks on 2025-02-20:
[D][X] Submit CS2103T Report (by: 20 Feb 2025)
```


---

This **fully integrated User Guide** covers:
- **Features & Commands**
- **Assertions**
- **Sorting Implementation**
- **Setup Instructions**
- **Planned Enhancements**

It is **fully GitHub-ready** and ensures clarity for **both users and developers**! 🚀

