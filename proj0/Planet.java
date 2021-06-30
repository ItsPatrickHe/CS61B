import java.lang.Math;

public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}

	public double calcDistance (Planet p){
		double x_square;
		double y_square;
		double distance;
		x_square = Math.pow(xxPos - p.xxPos, 2);
		y_square = Math.pow(yyPos - p.yyPos, 2);
		distance = Math.sqrt(x_square + y_square);
		return distance;
	}

	public double calcForceExertedBy (Planet p){
		double g = 6.67e-11;
		double distance = calcDistance(p);
		double force = (g * p.mass * mass) / Math.pow(distance, 2);
		return force;
	}

	public double calcForceExertedByX (Planet p){
		double f_total = calcForceExertedBy(p);
		double d = calcDistance(p);
		double dx = p.xxPos - xxPos;
		double fx = f_total * dx / d;
		return fx;
	} 

	public double calcForceExertedByY (Planet p){
		double f_total = calcForceExertedBy(p);
		double d = calcDistance(p);
		double dy = p.yyPos - yyPos;
		double fy = f_total * dy / d;
		return fy;
	}

	public double calcNetForceExertedByX (Planet[] p_lst){
		double net_x = 0;
		for (Planet p : p_lst){
			if (this.equals(p)){
				;
			} else{
				net_x += calcForceExertedByX(p);
			}
		}
		return net_x;
	}

	public double calcNetForceExertedByY (Planet[] p_lst){
		double net_y = 0;
		for (Planet p : p_lst){
			if (this.equals(p)){
				;
			} else{
				net_y += calcForceExertedByY(p);
			}
		}
		return net_y;
	}

	public void update (double dt, double fX, double fY){
		double acc_x = fX / mass;
		double acc_y = fY / mass;
		double new_xxVel = xxVel + acc_x * dt;
		double new_yyVel = yyVel + acc_y * dt;
		double new_xxPos = xxPos + new_xxVel * dt;
		double new_yyPos = yyPos + new_yyVel * dt;
		xxPos = new_xxPos;
		yyPos = new_yyPos;
		xxVel = new_xxVel;
		yyVel = new_yyVel;
	}

	public void draw (){
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);
	}
}