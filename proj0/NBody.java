public class NBody{
	public static double readRadius (String filename){
		In in = new In(filename);

		int n_p = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets (String filename){
		In in = new In(filename);

		int n_p = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[n_p];

		for (int i = 0; i < n_p; i = i + 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return planets;
	}

	public static void main (String[] args){
		/* Pass command lines */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		String imageToDraw = "images/starfield.jpg";

		for(double timing = 0; timing <= T; timing = timing + 1 ){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i = i + 1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int p = 0; p < planets.length; p = p + 1){
				planets[p].update(dt, xForces[p], yForces[p]);
			}

			/* Start drawing */
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius, radius);

			/* Clears the drawing window. */
			StdDraw.clear();

			/* Stamps three copies of advice.png in a triangular pattern. */
			StdDraw.picture(0, 0, imageToDraw);

			for (Planet p : planets){
				p.draw();
			}

			/* Shows the drawing to the screen, and waits 2000 milliseconds. */
			StdDraw.show();
			StdDraw.pause(10);

			T = T + dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
	    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
	                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
	    }
		
	} 
}