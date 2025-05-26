# Develop an Expense Tracker

## 1. Problem Description

Build an **Expense Tracker** microservice that helps users log daily expenses and visualize their spending patterns. The system should allow users to add expenses with categories, view spending summaries, and get insights into their financial habits.

### Core Requirements:
- **Frontend**: Frontend interface for adding expenses and viewing summaries
- **Backend**: RESTful API (.NET Core, Python Flask, or Node.js Express) for expense management
- **Data Management**: Store expenses with amount, category, date, and description
- **Visualization**: Simple charts/graphs showing spending by category and over time
- **Categories**: Predefined spending categories (Food, Transport, Entertainment, etc.)

### User Stories:
1. As a user, I can add a new expense with amount, category, and description
2. As a user, I can view all my expenses in a list format
3. As a user, I can see total spending for the current month
4. As a user, I can view spending breakdown by category
5. As a user, I can delete expenses I added by mistake

## 2. Hints

### Technical Implementation Hints:
- **Data Structure**: Simple expense object with id, amount, category, date, description
- **Storage**: In-memory array/list (no database needed)
- **Categories**: Fixed list like ["Food", "Transport", "Entertainment", "Shopping", "Bills"]
- **Charts**: Use Chart.js, Recharts, or simple CSS bars for visualization
- **Date Handling**: Use current date as default, allow manual date selection

### Quick Start Tips:
- Start with add/list functionality first
- Use form validation for amount (must be positive number)
- Keep categories simple - just 5-6 predefined options
- Focus on current month data to keep calculations simple
- Use mock data initially to test the UI

### Agent Mode Note:
If you are using the Agent mode and notice that the agent is working in the wrong directory, try to add the following line to your prompt:
```markdown
Work in the directory backend. When run terminal remember to change the right directory, for example: `cd challenges/expensetracker/backend && <command>`.
```
Change the directory to `frontend` when working on the frontend part.

## 3. Time Needed

**Estimated Duration: 45-60 minutes**

### Time Breakdown:
- **Setup** (5-10 minutes): Project structure, dependencies
- **Backend CRUD** (15-20 minutes): Add expense, list expenses, delete expense endpoints
- **Frontend Forms** (15-20 minutes): Add expense form, expense list, basic styling
- **Summary/Charts** (10-15 minutes): Category totals, simple visualization

## 4. Extra Features (Bonus Challenges)

Quick additions for teams that finish early:

### Easy Additions (5-10 minutes each):
- **Edit Expenses**: Modify existing expense entries
- **Date Filtering**: Filter expenses by date range
- **Expense Search**: Search expenses by description
- **Export Data**: Download expenses as CSV

### Medium Additions (10-15 minutes each):
- **Budget Limits**: Set monthly budgets per category with warnings
- **Recurring Expenses**: Add monthly recurring expenses automatically
- **Tags**: Add custom tags to expenses for better organization
- **Receipt Notes**: Add notes or receipt numbers to expenses

### Why Perfect for 1-Hour Challenge:
- **Relatable Problem**: Everyone tracks expenses or should
- **Clear CRUD Operations**: Standard create, read, delete patterns
- **Simple Calculations**: Basic arithmetic for totals and averages
- **Visual Results**: Charts and summaries provide immediate feedback
- **Practical Utility**: Participants build something actually useful

### Success Criteria:
- ✅ Can add new expenses
- ✅ Can view list of expenses
- ✅ Can see monthly total
- ✅ Can delete expenses
- ✅ Shows category breakdown
- ✅ Basic styling/layout

This challenge combines practical business logic, data visualization, and user experience design - perfect for demonstrating real-world application development skills in a short timeframe!