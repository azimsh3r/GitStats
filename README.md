Instructions on how to use!
https://drive.google.com/file/d/1AmZ-Mv1H2jk4ceVQ5VgO2fARO9j164Et/view?usp=sharing

### GitStats Web Application

#### Overview
This web application provides statistical analysis and insights for GitHub repositories. It allows users to authenticate via GitHub credentials and access information about repositories and contributors.

#### Controllers
1. **AuthController**
   - Path: `/auth`
   - Responsible for user authentication.
   - **Endpoints:**
     - `/login` (GET): Renders the authentication page.

2. **StatsController**
   - Path: `/stats`
   - Manages statistical data retrieval.
   - **Endpoints:**
     - `/` (GET): Renders the main statistics page.
     - `/` (POST): Retrieves repository information based on user input.
     - `/repoAnalytics` (POST): Retrieves contributor analytics for a specific repository.

#### Security
1. **AuthProvider**
   - Custom authentication provider.
   - Authenticates users using GitHub credentials.

#### Services
1. **AuthService**
   - Manages user authentication with GitHub.
   - Retrieves user data from the GitHub API using OAuth tokens.

2. **StatsService**
   - Handles statistical analysis of repositories and contributors.
   - Utilizes the GitHub API to retrieve repository and contributor information.

#### Packages
- `com.gitstats.project.controllers`: Contains controllers responsible for handling HTTP requests.
- `com.gitstats.project.security`: Implements security-related functionality, including authentication.
- `com.gitstats.project.services`: Contains services for retrieving and processing data.

#### Usage
1. **Authentication**
   - Navigate to `/auth/login` to log in using GitHub credentials.

2. **Statistics**
   - Once authenticated, access statistical insights by navigating to `/stats`.
   - Enter a GitHub username and repository name to view repository statistics.
   - Additional analytics for individual repositories are available under `/stats/repoAnalytics`.

#### Note
- This application requires valid GitHub credentials for authentication.
- Ensure proper access permissions to GitHub repositories for accurate statistical analysis.
