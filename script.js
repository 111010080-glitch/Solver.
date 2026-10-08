const readline = require('readline');

function createPrompt() {
    return readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
}

function askQuestion(rl, query) {
    return new Promise(resolve => {
        rl.question(query, answer => {
            resolve(answer);
        });
    });
}

async function getValidDouble(rl, prompt, nonZero = false) {
    while (true) {
        const input = await askQuestion(rl, prompt);
        const num = parseFloat(input.trim());
        
        if (!isNaN(num)) {
            if (nonZero && num === 0) {
                console.log("Error: 'a' cannot be zero in a quadratic equation.");
                continue;
            }
            return num;
        } else {
            console.log("Error: Please enter a valid number.");
        }
    }
}

async function main() {
    const rl = createPrompt();

    console.log("--- Quadratic Equation Solver ---");
    console.log("Form: ax² + bx + c = 0\n");

    // Get coefficient 'a'
    const a = await getValidDouble(rl, "Enter coefficient a (cannot be 0): ", true);

    // Get coefficient 'b'
    const b = await getValidDouble(rl, "Enter coefficient b: ");

    // Get coefficient 'c'
    const c = await getValidDouble(rl, "Enter coefficient c: ");

    // Display the user's equation
    console.log(`\nSolving: ${a}x² + ${b}x + ${c} = 0`);

    // Calculate the discriminant
    const discriminant = (b * b) - (4 * a * c);

    // Calculate and print the roots based on the discriminant
    if (discriminant > 0) {
        const root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        const root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
        console.log(`Two distinct real roots: x1 = ${root1.toFixed(4)}, x2 = ${root2.toFixed(4)}`);
    } 
    else if (discriminant === 0) {
        const root = -b / (2 * a);
        console.log(`One repeated real root: x = ${root.toFixed(4)}`);
    } 
    else {
        const realPart = -b / (2 * a);
        const imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
        console.log(`Complex roots: x1 = ${realPart.toFixed(4)} + ${imaginaryPart.toFixed(4)}i, x2 = ${realPart.toFixed(4)} - ${imaginaryPart.toFixed(4)}i`);
    }

    rl.close();
}

main();
