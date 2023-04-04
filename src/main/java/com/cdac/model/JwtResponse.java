package com.cdac.model;

public class JwtResponse {
		String token;

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		@Override
		public String toString() {
			return "JwtResponse [token=" + token + ", getToken()=" + getToken() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

		public JwtResponse(String token) {
			super();
			this.token = token;
		}

		public JwtResponse() {
			super();
		}
		
		
		
}
