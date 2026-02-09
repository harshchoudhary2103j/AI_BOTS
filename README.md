# AI_BOTS – Spring AI Mini Projects

A Spring Boot application demonstrating **modern AI backend patterns** using **Spring AI**, **LLMs**, **Vector Databases (pgvector)**, and **RAG (Retrieval-Augmented Generation)**.

This project is structured as **multiple mini-projects inside a single Spring Boot application**, each showcasing a real-world AI backend use case.

---

## 🚀 Tech Stack

- **Java 23**
- **Spring Boot 4.x**
- **Spring AI (ChatClient, VectorStore, Advisors)**
- **PostgreSQL + pgvector**
- **Docker Desktop**
- **DBeaver (DB client)**
- **OpenAI / Ollama (profile-based switching)**

---

---

## 🧠 Mini Projects Implemented

### ✅ Project 1 – The Sarcastic Poet

**Goal:**  
Generate sarcastic poems using LLMs with **structured output**.

**Endpoint**
GET /poem?topic={topic}&lang={language}


**Features**
- Uses `ChatClient` fluent API
- Structured JSON output mapped to DTO
- Profile-based model switching (Ollama / OpenAI)
- No database usage (pure AI response)

**Sample Response**
```json
{
  "title": "Meetings That Could Have Been Emails",
  "poemText": "Oh joy, another call at nine...",
  "rhymeScheme": "AABB"
}

# 🎧 Vibe Playlist Matcher

A Spring Boot + Spring AI project that matches a user’s emotional state with a song using **semantic similarity**, **vector embeddings**, and **pgvector**.

This project demonstrates how to build an **AI-powered recommendation system** without relying on keyword matching.

---

## 🚀 Tech Stack

- Java 23
- Spring Boot 4.x
- Spring AI
- PostgreSQL + pgvector
- Docker Desktop
- DBeaver
- OpenAI / Ollama (embeddings)

---

## 🎯 Problem Statement

Traditional search fails when users express feelings indirectly.

Example input:
"I feel broken and want to give up"


Even if the word *sad* is not present in the song lyrics, the system should still return a semantically relevant song.

---

## 🧠 Solution Overview

This project uses **vector embeddings** to understand meaning rather than keywords.

### High-Level Flow

Song Meaning → Embedding → Vector Store (pgvector)
User Feeling → Embedding → Similarity Search
Metadata Filter (genre = Rock)
Best Match Returned

---

## 📡 API Endpoint

### Match Vibe

GET /match-vibe?feeling={text}


### Example Response

```json
{
  "song": "Fix You",
  "genre": "Rock",
  "reason": "Matched using semantic similarity on your mood"
}


