Task Entity
The Task class represents a task with attributes suitable for serialization/deserialization to/from JSON format which includes:
* id: Unique identifier for the task.
* description: Description of the task.
* completed: Indicates whether the task is completed or not.

Serialization/Deserialization

TaskRepository
The TaskRepository class manages reading and writing tasks to a JSON file. It supports basic operations such as:

* Adding tasks
* Updating tasks
* Deleting tasks
* Retrieving tasks from the JSON file
* TaskController
