# AI Test Triage: example project

A tiny Java shop backend with tests that **fail on purpose**, so you can see
[AI Test Triage](https://github.com/Rushi7795/ai-test-triage) work without
needing a broken test suite of your own.

## See it in action (no setup needed)

- **Latest run:** open the [Actions tab](../../actions), click the newest
  "Tests with AI triage" run, and scroll down to **AI Test Triage** on the
  summary page.
- **On a pull request:** open the [demo pull request](../../pulls). The
  triage is posted there as a comment.

The run shows a red cross. That is expected: the tests are broken on purpose.
The triage step itself never changes the build result.

## What is broken, and what the AI said

9 tests, 5 pass, 4 fail. Each failure has a different real cause, the kind a
team has to sort out after a CI run goes red.

| Test | What is actually wrong | Expected | AI said | Our take |
|---|---|---|---|---|
| `CartTest.totalMultipliesPriceByQuantity` | `Cart.total()` ignores the quantity, so 2 x 29.99 comes out as 29.99 | PRODUCT BUG | PRODUCT BUG | Correct |
| `OrderServiceTest.placingAnOrderStoresIt` | The test never assigns its `repository` field, so the test itself throws a NullPointerException | TEST BUG | TEST BUG | Correct |
| `PaymentClientTest.chargeIsAccepted` | The test expects a payment gateway on `localhost:8089`, which is not running in CI | ENVIRONMENT/FLAKY | TEST BUG or ENVIRONMENT/FLAKY (varies between runs) | Defensible either way. A unit test that calls a live server is arguably a badly designed test, and the suggested fix (use a stub server) is the right one in both cases |
| `NotificationServiceTest.confirmationIsSent` | The email step takes about 300 ms, but the test only waits 100 ms | ENVIRONMENT/FLAKY | UNCLEAR | Fair. The report only says "timed out"; the 300 ms delay is in the source code, which the triage step never sees. It asked for more evidence instead of guessing |

Results across the first three runs on this repo. AI answers can vary slightly between runs.
Two or three exact matches per run, the rest reasonable disagreements: the triage is a fast first
opinion for a person to confirm, not a final verdict.

## Try it on your own fork

1. Fork this repo.
2. In your fork, go to **Settings > Secrets and variables > Actions** and add a
   secret named `ANTHROPIC_API_KEY` with your Anthropic API key.
3. Go to **Actions**, enable workflows, open "Tests with AI triage" and click
   **Run workflow**.

Without the secret the step still runs and lists the failures, just without
the AI explanation.

## Use it in your own project

Add this after your test step (any runner that writes JUnit XML):

```yaml
- name: AI test triage
  if: failure()
  uses: Rushi7795/ai-test-triage@v1
  with:
    anthropic-api-key: ${{ secrets.ANTHROPIC_API_KEY }}
```

For pull request comments, give the job `permissions: pull-requests: write`.
Full options: [Rushi7795/ai-test-triage](https://github.com/Rushi7795/ai-test-triage).
