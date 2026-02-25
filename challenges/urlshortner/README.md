# URL Shortener Service - Quick Challenge

## 1. Problem Description

Build a **URL Shortener** microservice (like bit.ly or tinyurl) that converts long URLs into short, shareable links. The system should generate unique short codes for long URLs and redirect users when they visit the short links.

### Core Requirements:
- **Frontend**: Simple React form to submit URLs and display shortened links
- **Backend**: RESTful API (.NET Core, Python Flask, or Node.js Express)
- **Core Logic**: Generate unique short codes and store URL mappings
- **Redirection**: Handle redirects from short URLs to original URLs

### User Stories:
1. As a user, I can paste a long URL and get a shortened version
2. As a user, I can click on a shortened URL and be redirected to the original
3. As a user, I can see a list of my recently shortened URLs
4. As a user, I get an error message for invalid URLs

## 2. Hints

### Technical Implementation Hints:
- **Short Code Generation**: Use random alphanumeric strings (6-8 characters) or base62 encoding
- **Storage**: Simple in-memory dictionary/map or array for URL mappings
- **Validation**: Check if input is a valid URL format
- **Frontend**: Single page with input form and results list
- **API Endpoints**: Just 2-3 endpoints needed: create short URL, redirect, and optional list

### Quick Start Tips:
- Start with backend API first - it's simpler
- Use existing URL validation libraries
- Keep the UI minimal - focus on functionality
- Test with a few sample URLs to verify redirects work

## 3. Time Needed

**Estimated Duration: 45-60 minutes**

### Time Breakdown:
- **Setup** (5-10 minutes): Create project structure, install dependencies
- **Backend API** (20-25 minutes): URL shortening endpoint, redirect endpoint, data storage
- **Frontend** (15-20 minutes): Simple React form, display results, basic styling
- **Integration & Testing** (5-10 minutes): Connect frontend to backend, test redirects

## 4. Extra Features (Bonus Challenges)

Quick additions if participants finish early:

### Easy Additions (5-10 minutes each):
- **Click Counter**: Track how many times each short URL was accessed
- **Custom Short Codes**: Allow users to specify their own short code
- **URL Preview**: Show original URL when hovering over short link
- **Copy to Clipboard**: One-click copy button for short URLs

### Medium Additions (10-15 minutes each):
- **QR Code Generation**: Generate QR codes for short URLs
- **Expiration Date**: Set expiration for short URLs
- **Basic Analytics**: Show creation date and last accessed time
- **Bulk Shortening**: Upload multiple URLs at once

## 5. Solution Description

### Architecture Overview:
Simple client-server architecture with minimal components - perfect for rapid development with AI coding assistance.

### Backend Implementation:
- **Framework**: Java, Express.js, Flask, or .NET Core minimal API
- **Data Storage**: In-memory object/dictionary (no database needed)
- **Core Logic**: 
  - Generate random 6-character alphanumeric codes
  - Store mapping between short codes and original URLs
  - Handle redirects with HTTP 302 status

### API Endpoints:
```
POST /api/shorten
  Body: { "url": "https://example.com/very/long/url" }
  Response: { "shortUrl": "http://localhost:3000/abc123", "shortCode": "abc123" }

GET /{shortCode}
  Redirects to original URL or returns 404 if not found

GET /api/urls (optional)
  Returns list of all shortened URLs
```

### Frontend Implementation:
- **Single Page App**: One React component with form and results
- **State**: Store list of shortened URLs in component state
- **UI Elements**:
  - URL input field with validation
  - Submit button
  - Results section showing original and shortened URLs
  - Error messages for invalid inputs


### Key Features:
1. **Instant Results**: Users see shortened URLs immediately
2. **Working Redirects**: Short URLs actually redirect to original URLs
3. **Error Handling**: Clear feedback for invalid URLs
4. **Clean UI**: Minimal but functional interface
5. **Testable**: Easy to verify functionality with sample URLs
